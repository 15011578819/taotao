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
 * 内容分类列表
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
            tbContentCategoryMapper.updateByPrimaryKey(category1);
        }
        return TaotaoResult.ok(category);
    }

    /**
     * 更新内容分类
     * @param id
     * @param name
     * @return
     */
    @Override
    public TaotaoResult updateContentCategory(long id, String name) {
        TbContentCategory category=new TbContentCategory();
        category.setId(id);
        category.setName(name);
        tbContentCategoryMapper.updateByPrimaryKeySelective(category);
        return TaotaoResult.ok();
    }

    /**
     * 删除内容列表
     * @param parentId
     * @param id
     * @return
     */
    @Override
    public TaotaoResult deleteContentCategory(Long parentId, long id) {
        System.out.println("parentId==="+parentId+"....."+"id===="+id);

        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
        if (parentId==null){
            parentId=category.getParentId();
        }
        //删除当前节点
        tbContentCategoryMapper.deleteByPrimaryKey(id);

        //判断当前节点是否为父节点
        if (category.getIsParent()){ //若为父节点，则删除下面所有子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(id);
            List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(example);
            if (tbContentCategories.size()>0&&tbContentCategories!=null){
                for (TbContentCategory  tbContentCategory:tbContentCategories) {
                    tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
                }
            }
        }
        System.out.println("parentId=22222=="+parentId+"....."+"id===="+id);
        // 判断父节点下是否还有子节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper
                .selectByExample(example);
        // 父节点
        TbContentCategory parentCat = tbContentCategoryMapper
                .selectByPrimaryKey(parentId);
        // 如果没有子节点，设置为false
        if (list != null && list.size() > 0) {
            parentCat.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKeySelective(parentCat);
        } else {
            parentCat.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKeySelective(parentCat);
        }

        return TaotaoResult.ok();
    }
}
