package com.crave.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/home","/"})
    public String home(){
        return "home";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
