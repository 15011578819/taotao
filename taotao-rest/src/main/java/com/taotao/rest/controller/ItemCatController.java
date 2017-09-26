package com.taotao.rest.controller;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-9-21.
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/itemcat/all")
    @ResponseBody
    public MappingJacksonValue  queryAll(String callback){
        CatResult catResult= itemCatService.getItemCatList();
        //包装成jsonp
        MappingJacksonValue value=new MappingJacksonValue(catResult);
        //设置回调的方法名
        value.setJsonpFunction(callback);
        return value;
    }
}
