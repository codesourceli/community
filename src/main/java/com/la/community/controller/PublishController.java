package com.la.community.controller;

import com.la.community.entity.Question;
import com.la.community.entity.User;
import com.la.community.service.IQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 发布问题
 */
@Controller
public class PublishController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("publish")
    public String publish(){
        return "publish";
    }


    @PostMapping("publish")
    public String doPublish(Question question, HttpServletRequest request, Model model){
        User user= (User) request.getSession().getAttribute("user");
        question.setCreatTime(System.currentTimeMillis());
        question.setModifyTime(System.currentTimeMillis());
        question.setCreatorId(user.getId());
        int result = questionService.insert(question);
        if(result>=1){
            System.out.println(question);
            model.addAttribute("question",question);
            return "redirect:/";
        }
        return "publish";
    }






}
