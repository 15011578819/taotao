package com.taotao.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TreeNode;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017-9-26.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<TreeNode> contentCategoryLsit = contentCategoryService.getContentCategoryLsit(parentId);
        return contentCategoryLsit;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(long parentId,String name){
        return contentCategoryService.createContentCategory(parentId,name);
    }
}
