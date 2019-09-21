package com.la.community.controller;

import com.la.community.dto.PaginationDTO;
import com.la.community.entity.User;
import com.la.community.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("profile")
    public String profile(Model model,PaginationDTO paginationDTOParam,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //question
        PaginationDTO paginationDTO=questionService.getQuestions(user,paginationDTOParam);

        model.addAttribute("paginationDTO",paginationDTO);
        //message

        //collection

        return "homePage";
    }

}
