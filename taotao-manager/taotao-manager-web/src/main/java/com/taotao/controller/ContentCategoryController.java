package com.taotao.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TreeNode;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类列表管理器
 * Created by Administrator on 2017-9-26.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    /**
     * 查询内容分类列表
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<TreeNode> contentCategoryLsit = contentCategoryService.getContentCategoryLsit(parentId);
        return contentCategoryLsit;
    }

    /**
     * 新增内容分类
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(long parentId,String name){
        return contentCategoryService.createContentCategory(parentId,name);
    }


    /**
     * 内容分类列表重命名
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id,String name){
        return contentCategoryService.updateContentCategory(id,name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(Long parentId,long id){
        System.out.println("parentId=="+parentId+"     id===="+id);
        TaotaoResult result=contentCategoryService.deleteContentCategory(parentId,id);
        return result;
    }

}
