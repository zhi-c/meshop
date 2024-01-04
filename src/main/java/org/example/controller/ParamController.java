package org.example.controller;

import org.example.pojo.Param;
import org.example.pojo.Result;
import org.example.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param")
@Validated
public class ParamController {
    @Autowired
    ParamService paramService;
    @PostMapping("/findallparams.do")
    public Result findAllParams(){

        Param param = paramService.findParam();
        return Result.success(param);
    }
}
