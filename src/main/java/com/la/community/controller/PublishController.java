package com.la.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 发布问题
 */
@Controller
public class PublishController {

    @GetMapping("publish")
    public String publish(){

        return "publish";
    }

}
