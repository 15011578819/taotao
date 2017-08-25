package com.taotao.service;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

/**
 * Created by Administrator on 2017-8-17.
 */
public interface ItemService {

   public  TbItem getItemById(long itemId);
   public EUDataGridResult getItemList(int page,int rows);
   public TaotaoResult addItem(TbItem tbItem, TbItemDesc tbItemDesc,String itemParams);
   public TaotaoResult updateItem(TbItem tbItem,TbItemDesc tbItemDesc,String itemDesc);
   public TaotaoResult deleteItem(Long [] ids);
   public TaotaoResult updateItemState(Long[] itemId);
   public TaotaoResult updateNormalState(Long[] itemId);
   public TaotaoResult getItemDesc(Long itemId);
}
