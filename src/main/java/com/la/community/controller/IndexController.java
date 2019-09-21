package com.la.community.controller;

import com.la.community.dto.PaginationDTO;
import com.la.community.dto.QuestionDTO;
import com.la.community.entity.User;
import com.la.community.service.IQuestionService;
import com.la.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 进入首页自动根据cookie登录
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IQuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,PaginationDTO paginationDTOParam,Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length>0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    User user = userService.findUserByGithubId(cookie.getValue());
                    request.getSession().setAttribute("user",user);
                    break;
                }
            }
        }

        PaginationDTO paginationDTO = questionService.list(paginationDTOParam);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }



}
