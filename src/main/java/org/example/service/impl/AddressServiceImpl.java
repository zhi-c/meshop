package org.example.service.impl;

import org.example.mapper.AddressMapper;
import org.example.pojo.Address;
import org.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    @Override
    public Address findAddressById(Integer id) {
        return  addressMapper.findAddressById(id);
    }

    @Override
    public List<Address> setDefault(Integer id, Integer userId) {
        addressMapper.setNoDefault(userId);
        addressMapper.setDefault(id,userId);
        List<Address> addressList = addressMapper.findAddressByUserId(userId);
        return addressList;
    }

    @Override
    public List<Address> findAddressByUserId(Integer userId) {
        List<Address> addressList = addressMapper.findAddressByUserId(userId);
        return addressList;
    }

    @Override
    public List<Address> delAddress(Integer id, Integer userId) {
        addressMapper.delAddress(id);
        List<Address>  addressList = addressMapper.findAddressByUserId(userId);
        return addressList;
    }

    @Override
    public void saveAddress(Integer userId, String name, String mobile, String province, String city, String district, String addr, String zip) {
        addressMapper.saveAddress(userId,name,mobile,province,city,district,addr,zip);
    }

    @Override
    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }
}
