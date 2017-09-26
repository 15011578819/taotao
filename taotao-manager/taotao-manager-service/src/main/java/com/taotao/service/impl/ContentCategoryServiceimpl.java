package com.taotao.service.impl;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TreeNode;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-9-26.
 */
@Service
public class ContentCategoryServiceimpl implements ContentCategoryService {

    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    /**
     * 查询内容分类
     * @param parentId
     * @return
     */
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

    /**
     * 新增内容分类
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public TaotaoResult createContentCategory(long parentId,String name) {
        TbContentCategory category=new TbContentCategory();
        category.setName(name);
        Date date=new Date();
        category.setCreated(date);
        category.setUpdated(date);
        category.setParentId(parentId);
        category.setIsParent(false);
        category.setStatus(1);
        category.setSortOrder(1);
        tbContentCategoryMapper.insert(category);
        //判断父节点 isParent是否为true，不为true改为true
        TbContentCategory category1 = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!category1.getIsParent()){
            category1.setIsParent(true);
        }
        return TaotaoResult.ok(category);
    }
}
