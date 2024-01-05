package org.example.pojo;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private Integer userId;
    private String name;
    private String phone;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String addr;
    private String zip;
    private Integer dfault;
    private Integer isDel;
    private String created;
    private String updated;
}
