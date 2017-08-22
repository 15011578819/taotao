package com.taotao.service;

import com.taotao.pojo.TreeNode;

import java.util.List;

/**
 * Created by Administrator on 2017-8-18.
 */
public interface ItemCatService {
    public List<TreeNode> getItemCatList(long parentId);
}
