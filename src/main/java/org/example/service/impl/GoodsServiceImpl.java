package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.GoodsMapper;

import org.example.pojo.Goods;
import org.example.pojo.PageBean;
import org.example.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> findGoodsFloors(Integer num) {
        return goodsMapper.findGoodsFloors(num);
    }

    @Override
    public Integer findGoodsSum() {
        return goodsMapper.findGoodsSum();
    }

    @Override
    public List<Goods> findHotProducts(String num) {

        return goodsMapper.findHotProducts(Integer.valueOf(num));
    }

    @Override
    public Goods findGoodsByProductId(String productid) {

        return goodsMapper.findGoodsByProductId(Integer.valueOf(productid));
    }

    @Override
    public PageBean<Goods> list(String productTypeId, String partsId, String pageNum, String pageSize, String name) {
        PageBean<Goods> goodsPageBean = new PageBean<>();


        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Goods> list = goodsMapper.list(productTypeId,partsId,name);
        //page中提供了方法，可以获取PageHelper分页查询后得到的记录条数和当前页数据
        Page<Goods> page = (Page<Goods>) list;

        int totalPage = (int) (page.getTotal()/page.getPageSize()+ ((page.getTotal()%page.getPageSize()==0) ? 0 : 1));
        goodsPageBean.setPageNum(Integer.valueOf(pageNum));
        goodsPageBean.setPageSize(Integer.valueOf(pageSize));
        goodsPageBean.setTotalRecord(page.size());
        goodsPageBean.setTotalPage((long) totalPage);

        goodsPageBean.setStartIndex((int) page.getStartRow());
        goodsPageBean.setData(page.getResult());
        goodsPageBean.setPrePage(page.getPageNum()==1 ? 1 : page.getPageNum()-1);
        goodsPageBean.setNextPage(page.getPageNum() == totalPage ? totalPage : page.getPageNum()+1);

        return goodsPageBean;
    }

}
