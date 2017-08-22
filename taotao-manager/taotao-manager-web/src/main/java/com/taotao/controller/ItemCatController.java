package com.taotao.controller;

import com.taotao.pojo.TreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017-8-18.
 */
@Controller
@RequestMapping(value = "/item/cat")
public class ItemCatController {

    @Autowired
    ItemCatService service;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<TreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<TreeNode> result=service.getItemCatList(parentId);
        return result;
    }
}
