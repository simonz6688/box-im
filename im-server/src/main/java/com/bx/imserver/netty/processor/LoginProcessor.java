package com.bx.imserver.netty.processor;

import cn.hutool.core.bean.BeanUtil;
import com.bx.imcommon.contant.Constant;
import com.bx.imcommon.contant.RedisKey;
import com.bx.imcommon.enums.IMCmdType;
import com.bx.imcommon.model.IMSendInfo;
import com.bx.imcommon.model.LoginInfo;
import com.bx.imcommon.util.JwtUtil;
import com.bx.imserver.netty.IMServerGroup;
import com.bx.imserver.netty.UserChannelCtxMap;
import com.bx.imserver.netty.ws.WebSocketServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LoginProcessor extends   MessageProcessor<LoginInfo> {


    @Autowired
    private WebSocketServer WSServer;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Value("${jwt.accessToken.secret}")
    private String accessTokenSecret;

    @Override
    synchronized public void process(ChannelHandlerContext ctx, LoginInfo loginInfo) {
        if(!JwtUtil.checkSign(loginInfo.getAccessToken(),accessTokenSecret)){
            ctx.channel().close();
            log.warn("用户token校验不通过，强制下线,token:{}",loginInfo.getAccessToken());
        }
        Long userId = JwtUtil.getUserId(loginInfo.getAccessToken());
        log.info("用户登录，userId:{}",userId);
        ChannelHandlerContext context = UserChannelCtxMap.getChannelCtx(userId);
        if(context != null && !ctx.channel().id().equals(context.channel().id())){
            // 不允许多地登录,强制下线
            IMSendInfo sendInfo = new IMSendInfo();
            sendInfo.setCmd(IMCmdType.FORCE_LOGUT.code());
            sendInfo.setData("您已在其他地方登陆，将被强制下线");
            context.channel().writeAndFlush(sendInfo);
            log.info("异地登录，强制下线,userId:{}",userId);
        }
        // 绑定用户和channel
        UserChannelCtxMap.addChannelCtx(userId,ctx);
        // 设置用户id属性
        AttributeKey<Long> attr = AttributeKey.valueOf("USER_ID");
        ctx.channel().attr(attr).set(userId);
        // 心跳次数
        attr = AttributeKey.valueOf("HEARTBEAt_TIMES");
        ctx.channel().attr(attr).set(0L);
        // 在redis上记录每个user的channelId，15秒没有心跳，则自动过期
        String key = RedisKey.IM_USER_SERVER_ID+userId;
        redisTemplate.opsForValue().set(key, IMServerGroup.serverId, Constant.ONLINE_TIMEOUT_SECOND, TimeUnit.SECONDS);
        // 响应ws
        IMSendInfo sendInfo = new IMSendInfo();
        sendInfo.setCmd(IMCmdType.LOGIN.code());
        ctx.channel().writeAndFlush(sendInfo);
    }


    @Override
    public LoginInfo transForm(Object o) {
        HashMap map = (HashMap)o;
        LoginInfo loginInfo = BeanUtil.fillBeanWithMap(map, new LoginInfo(), false);
        return  loginInfo;
    }
}
