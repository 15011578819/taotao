package com.taotao.controller;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017-8-17.
 *
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询单个商品
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem tbItem=itemService.getItemById(itemId);
        return tbItem;
    }

    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(int page,int rows){
        EUDataGridResult euDataGridResult=itemService.getItemList(page,rows);
        return euDataGridResult;
    }

    /**
     * 添加商品
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @RequestMapping(value = "/item/save" ,method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult addItem(TbItem tbItem,  String desc,String itemParams){
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        TaotaoResult result=itemService.addItem(tbItem,tbItemDesc,itemParams);
        System.out.println("TbItemDesc.desc==="+tbItemDesc.getItemDesc());
        return result;
    }

    /**
     * 商品删除
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/rest/item/delete",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult deleteItem(@RequestParam(value = "ids",defaultValue = "0") Long[] itemId){
        TaotaoResult result=itemService.deletItem(itemId);
        return result;
    }

    /**
     * 下架
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/rest/item/instock",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateItemState_down(@RequestParam(value = "ids",defaultValue = "0")long itemId){
        TaotaoResult result=itemService.updateItemState(itemId);
        return result;
    }

    /**
     * 上架
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/rest/item/reshelf",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateItemState_up(@RequestParam(value = "ids",defaultValue = "0")long itemId){
        TaotaoResult result=itemService.updateNormalState(itemId);
        return result;
    }
}
