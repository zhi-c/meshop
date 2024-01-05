package org.example.service;

import org.example.pojo.Address;

import java.util.List;

public interface AddressService {
    Address findAddressById(Integer id);

    List<Address> setDefault(Integer id,Integer userId);

    List<Address> findAddressByUserId(Integer userId);

    List<Address> delAddress(Integer id, Integer userId);

    void saveAddress(Integer userId, String name, String mobile, String province, String city, String district, String addr, String zip);
}
