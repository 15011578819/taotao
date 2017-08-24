package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017-8-24.
 */
@Controller
public class ItemParamItemController {

    @Autowired
    ItemParamItemService itemParamItemService;

    @RequestMapping("/showItem/{itemId}")
    @ResponseBody
    public ModelAndView getItemParanItem(@PathVariable long itemId){
        String result=itemParamItemService.getItemParamItem(itemId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("itemParam",result);
        mv.setViewName("item");
        return mv;
    }
}
