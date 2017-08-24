package com.taotao.controller;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-8-23.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;


    @RequestMapping(value = "/list")
    @ResponseBody
    public EUDataGridResult getItemParamList(Integer page,Integer rows){
        EUDataGridResult result=itemParamService.getItemParamList(page,rows);
        return result;
    }

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCatId(@PathVariable long itemCatId){
        TaotaoResult result=itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping(value = "save/{cid}",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult inserItemParam(@PathVariable long cid,String paramData){
        TbItemParam param=new TbItemParam();
        param.setItemCatId(cid);
        param.setParamData(paramData);
        TaotaoResult result=itemParamService.insertItemParam(param);
        return result;

    }
}
