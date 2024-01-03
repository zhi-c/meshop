package org.example.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T> {
    private Long totalPage;//总条数
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalRecord;
    private Integer startIndex;
    private List<T> data;//当前页数据集合
    private Integer prePage;
    private Integer nextPage;



}
