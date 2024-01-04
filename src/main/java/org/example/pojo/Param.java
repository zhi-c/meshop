package org.example.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Param{
    private Integer id;     //d 自动增长列
    private Integer parentId;   //父类ID，id为0时为根结点，为 一类节点
    private String name;    //类别名称
    private Integer sortOrder;  //同类展示顺序
    private Integer status;     //状态编码，1有效，0无效
    private Integer level;      //节点级别，顶层节点为0，一次类推
    private LocalDateTime created;  //创建时间
    private LocalDateTime updated;  //更新时间
    private List<Param> children;



}
