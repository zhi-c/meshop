package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findAccount(String account) {
        User u = userMapper.findByUserAccount(account);
        return u;
    }

    @Override
    public void register(String account, String password, String email, String phone, String question, String asw,Integer age,Integer sex) {
        //密码加密
        String md5String=Md5Util.getMD5String(password);

        //添加
        userMapper.add(account, md5String, email, phone, question, asw, age, sex);
    }

    @Override
    public User findEmail(String email) {
        User u = userMapper.findByUserEmail(email);
        return u;
    }

    @Override
    public User findPhone(String phone) {
        User u = userMapper.findByUserPhone(phone);
        return u;
    }
}
