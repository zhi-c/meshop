package org.example.service;

import org.example.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findAccount(String account);
    //注册
    void register(String account, String password, String email, String phone, String question, String asw,Integer age,Integer sex);

    User findEmail(String email);

    User findPhone(String phone);
    //更新用户信息
    void update(User user);
    //更新密码
    void updatePwd(String newPwd);

    void updatePwdNotLogin(Integer userId, String newPwd);
}
