package org.example.controller;


import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/do_login.do")
    public Result login(String account,String email){
       return null;
    }
    @PostMapping("/do_check_info.do")
    public Result registerInfo(String info,String type){
        User u = null;
        if(type.equals("account")){
            u = userService.findAccount(info);
            if(u==null){
                return Result.success("信息验证成功！");
            }else {
                return Result.error("用户名已经存在");
            }
        } else if (type.equals("email")) {
            u = userService.findEmail(info);
            if(u==null){
                return Result.success("信息验证成功！");
            }else {
                return Result.error("Email已经存在");
            }
        } else if(type.equals("phone")) {
            u = userService.findPhone(info);
            if(u==null){
                return Result.success("信息验证成功！");
            }else {
                return Result.error("电话号码已经存在！");
            }
        }else {
            return Result.error("信息验证类别错误！");
        }
    }
    @PostMapping("/do_register.do")
    public Result register(@Pattern(regexp = "^\\S{5,16}") String account, @Pattern(regexp = "^\\S{5,16}")String password, String email, String phone, String question, String asw, Integer age, Integer sex){
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
