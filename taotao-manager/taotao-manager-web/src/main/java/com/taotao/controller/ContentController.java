package com.taotao.controller;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017-9-27.
 */
@Controller
public class ContentController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(@RequestParam(value = "categoryId",defaultValue = "0")long categoryId,int page,int rows){
        EUDataGridResult euDataGridResult=contentService.getContentList(categoryId,page,rows);
        return euDataGridResult;
    }


    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent tbContent){
        TaotaoResult result = contentService.insertContent(tbContent);
        return result;
    }

}
