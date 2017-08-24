package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo ;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-8-23.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper tbItemParamMapper;
    @Autowired
    TbItemCatMapper tbItemCatMapper;

    @Override
    public EUDataGridResult getItemParamList(Integer page, Integer rows) {
        TbItemParamExample example=new TbItemParamExample();
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItemParam> itemParamList=tbItemParamMapper.selectByExampleWithBLOBs(example);
        EUDataGridResult result=new EUDataGridResult();
        result.setRows(itemParamList);
        //取记录总条数
        PageInfo<TbItemParam> pageInfo=new PageInfo<TbItemParam>(itemParamList);
        result.setTotal(pageInfo.getTotal());
        for (TbItemParam tbItemParam:itemParamList){
            Long itemId=tbItemParam.getItemCatId();
            TbItemCat tbItemCat=tbItemCatMapper.selectByPrimaryKey(itemId);
            tbItemParam.setItemCatName(tbItemCat.getName());
        }
        return result;

    }

    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample example=new TbItemParamExample();
        TbItemParamExample.Criteria criteria=example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list=tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()!=0){
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
        Date date=new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }
}
