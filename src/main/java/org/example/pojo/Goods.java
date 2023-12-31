package org.example.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Goods {
    private Integer id;     //商品编号，自动增长长列
    private String name;    //商品名称（配件）
    private Integer productId;  //产品类型编号，对应action_params表中parent_id为0的分类
    private Integer partsId;    //配件分类,对应action_params表中parent_id非0参数
    private String iconUrl;     //商品主图片
    private String subImages;   //图片地址，一组小图
    private String detail;      //商品详情
    private String specParam;   //规格参数
    private float price;        //价格：单位元，保留两位小数
    private Integer stock;      //库存
    private Integer status;     //商品的状态：1-待售，刚保存；2- 上架，在售；3-下架，停售；默认值为1
    private Integer isHot;      //是否热销，1-是，2-否；默认值为2
    private LocalDateTime created;  //创建时间
    private LocalDateTime updated;  //更新时间
}
