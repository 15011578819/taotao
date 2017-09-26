package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017-9-4.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView showIndex(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
