package com.la.community.controller;

import com.la.community.dto.QuestionDTO;
import com.la.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("question/{id}")
    public String getQuestion(@PathVariable(name = "id") Integer id,Model model){
        QuestionDTO questionDTO =questionService.getQuestionById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }






}
