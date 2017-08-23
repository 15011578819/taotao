package com.taotao.controller;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-8-17.
 *
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem tbItem=itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping(value = "/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(int page,int rows){
        EUDataGridResult euDataGridResult=itemService.getItemList(page,rows);
        return euDataGridResult;
    }

    @RequestMapping(value = "/item/save" ,method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult addItem(TbItem tbItem,  String desc){
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        TaotaoResult result=itemService.addItem(tbItem,tbItemDesc);
        System.out.println("TbItemDesc.desc==="+tbItemDesc.getItemDesc());
        return result;
    }

}
