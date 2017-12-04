package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yuderen
 * @version: 1.0 2017-9-16 20:49
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index/index";
    }

}
