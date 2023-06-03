package com.bx.implatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bx.implatform.entity.User;
import com.bx.implatform.dto.LoginDTO;
import com.bx.implatform.dto.RegisterDTO;
import com.bx.implatform.vo.LoginVO;
import com.bx.implatform.vo.UserVO;

import java.util.List;


public interface IUserService extends IService<User> {

    LoginVO login(LoginDTO dto);

    LoginVO refreshToken(String refreshToken);

    void register(RegisterDTO dto);

    User findUserByName(String username);

    void update(UserVO vo);

    List<UserVO> findUserByNickName(String nickname);

    List<Long> checkOnline(String userIds);

}
