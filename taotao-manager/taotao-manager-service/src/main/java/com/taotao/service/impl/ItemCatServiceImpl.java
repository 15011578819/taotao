package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-8-18.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper itemCatMapper;

    @Override
    public List<TreeNode> getItemCatList(long parentId) {
        TbItemCatExample example=new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> itemCatList=itemCatMapper.selectByExample(example);
        List<TreeNode> result=new ArrayList<>();
        for (TbItemCat tbItemCat:itemCatList){
            TreeNode treeNode=new TreeNode(tbItemCat.getId(),tbItemCat.getName(),
                    tbItemCat.getIsParent()?"closed":"open");
            result.add(treeNode);
        }
        return result;
    }
}
