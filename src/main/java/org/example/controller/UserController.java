package org.example.controller;


import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("do_login.do")
    public Result login(String account,String password){
        return null;
    }

    @RequestMapping("do_register.do")
    public Result register(String account,String password,String email,String phone,String question,String asw,Integer age,Integer sex){
        //查询用户
        User u = userService.findAccount(account);
        if(u==null){
            userService.register(account,password,email,phone,question,asw,age,sex);
            return Result.success();
        }else {
            return Result.error("用户名被占用");
        }

    }
}
