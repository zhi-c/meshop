package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一相应结果
@NoArgsConstructor  //无参构造方法
@AllArgsConstructor     //全参构造方法
@Data
public class Result<T> {
    private Integer status;     //业务状态码 0-成功，1-失败
    private String  msg;    //提示信息
    private T data;     //响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(0,"操作成功",data);
    }
    //快速返回操作响应结果

    public static Result success() {
        return new Result(0,"操作成功",null);
    }
    public static Result error(String msg){
        return new Result(1,msg,null);
    }
}
