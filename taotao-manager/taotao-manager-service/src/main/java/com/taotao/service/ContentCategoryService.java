package com.taotao.service;

import com.taotao.pojo.TreeNode;

import java.util.List;

/**
 * Created by Administrator on 2017-9-26.
 */
public interface ContentCategoryService {
    public List<TreeNode> getContentCategoryLsit(long parentId);
}
