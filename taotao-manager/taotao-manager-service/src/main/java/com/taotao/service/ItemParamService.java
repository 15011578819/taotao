package com.taotao.service;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * Created by Administrator on 2017-8-23.
 */
public interface ItemParamService {

    EUDataGridResult getItemParamList(Integer page,Integer rows);

    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam tbItemParam);
}
