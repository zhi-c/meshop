package org.example.mapper;

import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.example.pojo.Address;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Select("select * from address where id =#{id}")
    Address findAddressById(Integer id);
    @Update("update address set dfault = 0 where user_id = #{userId}")
    void setNoDefault(Integer userId);
    @Update("update address set dfault = 1 where user_id = #{userId} and id = #{id}")
    void setDefault(Integer id, Integer userId);

    @Select("select * from address where user_id = #{userId}")
    List<Address> findAddressByUserId(Integer userId);
    @Delete("delete from address where id = #{id}")
    void delAddress(Integer id);
    @Insert("insert into address(user_id,name,mobile,province,city,district,addr,zip,isDel,created,updated) " +
            "values(#{userId},#{name},#{mobile},#{province},#{city},#{district},#{addr},#{zip},0,now(),now()) ")
    void saveAddress(Integer userId, String name, String mobile, String province, String city, String district, String addr, String zip);
    @Update("update address set name = #{name},phone=#{phone},mobile=#{mobile},province=#{province},city = #{city}," +
            "district = #{district},addr = #{addr},zip=#{zip},dfault=#{dfault},isDel=#{isDel},updated=now()" +
            " where id = #{id}")
    void updateAddress(Address address);



}
