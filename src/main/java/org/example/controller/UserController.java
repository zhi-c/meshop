package org.example.controller;


import jakarta.validation.constraints.Pattern;

import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.JwtUtil;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //验证用户获取用户对象接口
    @PostMapping("/getUserByAccount.do")
    public Result getUserByAccount(String account){

        User user = userService.findAccount(account);
        if(user == null){
            return Result.error("用户名错误");
        }
        return Result.success(user);
    }
    //用户注销登出接口
    @PostMapping("do_logout.do")
    public Result logout(@RequestHeader(name = "Authorization")String token){
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success("已注销登录");
    }
    //用户信息修改接口
    @PostMapping("/updateuserinfo.do")
    public Result updateUserInfo(@RequestBody User user){
        userService.update(user);
        user = userService.findAccount(user.getAccount());
        return Result.success(user);
    }
    //更改用户密码
    @PostMapping("/updatepassword.do")
    public Result updatePassword(@RequestBody Map<String,String> params){
        //参数校验
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("确实必要的参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String account = (String) map.get("account");
        User loginUser = userService.findAccount(account);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码不正确");
        }
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一致");
        }
        //调用service
        userService.updatePwd(newPwd);
        return Result.success("密码修改成功");
    }
    //根据问题更改密码
    @PostMapping("resetpassword.do")
    public Result resetPassword(String newPwd,String id){
        userService.updatePwd(newPwd);
        return Result.success("密码修改成功！");
    }
    //验证用户密码问题答案
    @PostMapping("checkuserasw.do")
    public Result checkUserAsw(String account,String question,String asw){
        User user = userService.findAccount(account);
        if(!asw.equals(user.getAsw())){
            return Result.error("问题答案错误");
        }
        return Result.success("回答正确");
    }
    //用户登录接口
    @PostMapping("/do_login.do")
    public Result login(@Pattern(regexp = "^\\S{5,16}") String account, @Pattern(regexp = "^\\S{5,16}")String password){
       //根据用户名获取用户
        User loginUser = userService.findAccount(account);
        //判断用户名是否存在
       if(loginUser == null){
           return Result.error("用户名不存在");
       }else if(Md5Util.checkPassword(password,loginUser.getPassword())){
           Map<String,Object> claims = new HashMap<>();
           claims.put("id",loginUser.getId());
           claims.put("account",loginUser.getAccount());
           String token = JwtUtil.genToken(claims);
           // 把token存取到redis里面
           ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
           operations.set(token,token,1, TimeUnit.HOURS);
           return Result.success("登陆成功",token);
       }
       return Result.error("密码错误");
    }
    @PostMapping("getuserquestion.do")
    public Result getUserQuestion(String account){
        User user=userService.findAccount(account);
        String question = user.getQuestion();
        if(!(question==null)){
            return Result.success(question);
        }
        return Result.error("未设置密码提示问题");
    }
    //注册信息检测
    @PostMapping("/do_check_info.do")
    public Result registerInfo(String info, String type){
        User u = null;
        if(type.equals("account")){
            u = userService.findAccount(info);
            if(u==null){
                return Result.success("信息验证成功！",null);
            }else {
                return Result.error("用户名已经存在");
            }
        } else if (type.equals("email")) {
            u = userService.findEmail(info);
            if(u==null){
                return Result.success("信息验证成功！",null);
            }else {
                return Result.error("Email已经存在");
            }
        } else if(type.equals("phone")) {
            u = userService.findPhone(info);
            if(u==null){
                return Result.success("信息验证成功！",null);
            }else {
                return Result.error("电话号码已经存在！");
            }
        }else {
            return Result.error("信息验证类别错误！");
        }
    }
    //用户注册接口
    @PostMapping("/do_register.do")
    public Result register(@Pattern(regexp = "^\\S{5,16}") String account, @Pattern(regexp = "^\\S{5,16}")String password, String email, String phone, String question, String asw, Integer age, Integer sex){
        //查询用户
        User u = userService.findAccount(account);
        if(u==null){
            userService.register(account,password,email,phone,question,asw,age,sex);
            return Result.success("注册成功");
        }else {
            return Result.error("用户名被占用");
        }
    }
    @GetMapping("/getuserinfo.do")
    public Result<User> userInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String account = (String) map.get("account");
        User user = userService.findAccount(account);
        return Result.success(user);
    }
    //更改密码不用登录
    @PostMapping("/setnewpassword.do")
    public Result setNewPassword(Integer userId,@RequestBody Map<String,String> params){
        //参数校验
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("确实必要的参数");
        }
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一致");
        }
        //调用service
        userService.updatePwdNotLogin(userId,newPwd);
        return Result.success("密码修改成功");
    }

}
