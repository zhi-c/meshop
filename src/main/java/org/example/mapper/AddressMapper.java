package org.example.mapper;

import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.example.pojo.Address;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Select("select * from address where id =#{id}")
    Address findAddressById(Integer id);
    @Update("update address set dfault = 1 ")
    void setDefault(Integer id, Integer userId);

    @Select("select * from address where user_id = #{userId}")
    List<Address> findAddressByUserId(Integer userId);
    @Delete("delete from address where id = #{id}")
    void delAddress(Integer id);
    @Insert("insert into address(user_id,name,mobile,province,city,district,addr,zip,isDel,created,updated) " +
            "values(#{userId},#{name},#{mobile},#{province},#{city},#{district},#{addr},#{zip},0,now(),now()) ")
    void saveAddress(Integer userId, String name, String mobile, String province, String city, String district, String addr, String zip);

}
