package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

/**
 * Created by Administrator on 2017-8-17.
 */
@Controller
public class PageController {

    /**
     * 打开首页
     */
    @RequestMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/{page}")
    public ModelAndView showPage(@PathVariable String page){
        ModelAndView mv=new ModelAndView();
        mv.setViewName(page);
        return mv;
    }
}
