package com.taotao.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TreeNode;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * Created by Administrator on 2017-9-26.
 */
public interface ContentCategoryService {
    public List<TreeNode> getContentCategoryLsit(long parentId);

    public TaotaoResult  createContentCategory(long parentId,String name);

    TaotaoResult updateContentCategory(long id,String name);

    TaotaoResult deleteContentCategory(Long parentId,long id);
}
