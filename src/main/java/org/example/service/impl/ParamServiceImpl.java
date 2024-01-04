package org.example.service.impl;

import org.example.mapper.ParamMapper;
import org.example.pojo.Goods;
import org.example.pojo.GoodsCategory;
import org.example.pojo.Param;
import org.example.service.ParamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService {
    private ParamMapper paramMapper;





    @Override
    public Param findParam() {
        Param param =new Param();
        List<Param> listParam = paramMapper.findAll();

        return null;
    }
}
