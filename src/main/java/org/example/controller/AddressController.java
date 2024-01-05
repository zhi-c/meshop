package org.example.controller;

import org.example.pojo.Address;
import org.example.pojo.Result;
import org.example.service.AddressService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addr")
@Validated
public class AddressController {
    @Autowired
    AddressService addressService;



    @PostMapping("/findAddressById.do")
    public Result findAddressById(String id){
        Address address = addressService.findAddressById(Integer.valueOf(id));
        if(address==null){
            return Result.error("参数错误！");
        }
        return Result.success(address);
    }
    //设置默认地址
    @GetMapping("/setdefault.do")
    public Result setDefault(String id){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        List<Address> addressList = addressService.setDefault(Integer.valueOf(id),userId);
        if(addressList==null){
            return Result.success("默认地址设置失败！");
        }
        return Result.success(addressList);
    }
    //收货人地址列表接口
    @GetMapping("/findaddrs.do")
    public Result findUserAddress(){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        List<Address> addressList = addressService.findAddressByUserId(userId);
        return Result.success(addressList);
    }
    //删除收货人地址
    @GetMapping("/deladdr.do")
    public Result delAddress(String id){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        if(addressService.findAddressById(Integer.valueOf(id)) == null){
            return Result.success("删除地址失败！");
        }
        Integer userId = (Integer) map.get("id");
        List<Address> addressList = addressService.delAddress(Integer.valueOf(id),userId);
        return Result.success(addressList);
    }
    //增加地址
    @PostMapping("/saveaddr.do")
    public Result saveAddress(String name,String mobile,String province,String city,String district,String addr,String zip){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        addressService.saveAddress(userId,name,mobile,province,city,district,addr,zip);
        List<Address> addressList = addressService.findAddressByUserId(userId);
        return Result.success(addressList);
    }
}