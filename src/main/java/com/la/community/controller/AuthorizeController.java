package com.la.community.controller;

import com.la.community.dto.AccessTokenDTO;
import com.la.community.dto.GithubUser;
import com.la.community.entity.User;
import com.la.community.provider.GithubProvider;
import com.la.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private IUserService userService;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;


    @GetMapping("callback")
    public String callback(String code, String state, HttpServletRequest request, HttpServletResponse response){
        System.out.println("cookie");
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null){
            User user = new User();
            user=userService.findUserByGithubId(githubUser.getId());
            if (user!=null){
                request.getSession().setAttribute("user",user);
                //存入cookie
                Cookie cookie = new Cookie("token", user.getAccountId());
                response.addCookie(cookie);
                return "redirect:/";
            }
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId());
            user.setCreatTime(System.currentTimeMillis());
            user.setModifyTime(user.getCreatTime());
            int result = userService.insert(user);
            System.out.println(result);
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }






}
