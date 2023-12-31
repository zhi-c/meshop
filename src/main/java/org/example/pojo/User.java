package org.example.pojo;

import lombok.Data;

import java.time.LocalDateTime;
//lombok 在编译阶段，为实体类自动生成setter getter toString
//pom文件中引入依赖 在实体类上添加注解
@Data
public class User {
    private Integer id;     //用户id
    private String account;     //用户名
    private String password;    //密码，MD5加密
    private String email;       //邮箱
    private String phone;   //电话
    private String question;    //密码问题
    private String asw;     //找回密码答案
    private String role;    //角色 1-普通用户、2-管理员
    private String age;     //年龄，在0到120之间取值
    private Integer sex;     //性别，1：男、0：女
    private LocalDateTime createTime;  //创建时间
    private LocalDateTime updateTime;  //更新时间
    private Integer del;        //正常：0、删除：1，默认为0
    private String name;        //姓名

}
