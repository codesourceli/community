package com.la.community.controller;

import com.la.community.entity.User;
import com.la.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                User user = userService.findUserByGithubId(cookie.getValue());
                request.getSession().setAttribute("user",user);
                break;
            }
        }
        return "index";
    }



}
