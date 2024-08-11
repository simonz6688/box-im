package com.bx.imserver.netty.ws;

import cn.hutool.core.net.url.UrlBuilder;
import com.bx.imcommon.model.IMLoginInfo;
import com.bx.imserver.constant.ChannelAttrKey;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.AttributeKey;

import java.util.Optional;

public class HttpParamHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            UrlBuilder urlBuilder = UrlBuilder.ofHttp(request.uri());
            String token = Optional.ofNullable(urlBuilder.getQuery()).map(k->k.get("token")).map(CharSequence::toString).orElse("");
            IMLoginInfo imLoginInfo = new IMLoginInfo();
            imLoginInfo.setAccessToken(token);
            AttributeKey<IMLoginInfo> userToken = AttributeKey.valueOf(ChannelAttrKey.USER_TOKEN);
            ctx.channel().attr(userToken).set(imLoginInfo);
            // 获取请求路径
            request.setUri(urlBuilder.getPath().toString());
            ctx.pipeline().remove(this);
            ctx.fireChannelRead(request);
        }else {
            ctx.fireChannelRead(msg);
        }
    }

}