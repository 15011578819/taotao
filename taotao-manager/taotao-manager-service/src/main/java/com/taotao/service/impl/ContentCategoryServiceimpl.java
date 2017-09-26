package com.taotao.service.impl;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TreeNode;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-9-26.
 */
@Service
public class ContentCategoryServiceimpl implements ContentCategoryService {

    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TreeNode> getContentCategoryLsit(long parentId) {
        TbContentCategoryExample example=new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(example);
        List<TreeNode> resultList=new ArrayList<>();
        for (TbContentCategory tbContentCategory:tbContentCategories) {
            TreeNode treeNode=new TreeNode();
            treeNode.setId(tbContentCategory.getId());
            treeNode.setText(tbContentCategory.getName());
            treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
            resultList.add(treeNode);
        }
        return resultList;
    }
}
