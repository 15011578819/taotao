package com.taotao.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParamItem;

/**
 * Created by Administrator on 2017-8-24.
 */
public interface ItemParamItemService {

    String getItemParamItem(Long itemId);

    TaotaoResult getItemParamItem_(Long itemId);
}
