package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where account=#{account}")
    User findByUserAccount(String account);
    //根据手机号查询用户
    @Select("select * from user where phone = #{phone}")
    User findByUserPhone(String phone);
    //根据邮箱查询用户
    @Select("select * from user where email = #{email}")
    User findByUserEmail(String email);
    //添加
    @Insert("insert into user(account,password,email,phone,question,asw,age,sex,create_time,updata_time)" +
            "values(#{account},#{password},#{email},#{phone},#{question},#{asw},#{age},#{sex},now(),now())")
    void add(String account, String password, String email, String phone, String question, String asw,Integer age, Integer sex);


}
