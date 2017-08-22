package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-8-17.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /**
     * 获取商品列表信息
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample tbItemExample=new TbItemExample();
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItem> itemList=itemMapper.selectByExample(tbItemExample);
        EUDataGridResult result=new EUDataGridResult();
        result.setRows(itemList);
        //取记录总条数
        PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(itemList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult addItem(TbItem tbItem) {
        //添加商品id
        tbItem.setId(IDUtils.genItemId());
        //创建日期和更新日期
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //添加商品的状态
        tbItem.setStatus((byte) 1);
        itemMapper.insert(tbItem);
        return TaotaoResult.ok(tbItem);
    }
}
