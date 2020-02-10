package com.luhuihahaha.community.controller;

import com.luhuihahaha.community.dto.AccessTokenDTO;
import com.luhuihahaha.community.dto.GithubUser;
import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.model.User;
import com.luhuihahaha.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String githubClientId;

    @Value("${github.client.secret}")
    private String githubClientSecret;

    @Value("${github.redirect.uri}")
    private String githubRedirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(githubClientId);
        accessTokenDTO.setClient_secret(githubClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(githubRedirectUri);
        accessTokenDTO.setState(state);
        String accseeToken = githubProvider.getAccseeToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accseeToken);
        if (githubUser != null) {
            //登入成功 加入cookies,session
            User user = new User();
            user.setName(githubUser.getLogin());
            user.setAccountId(String.valueOf(githubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
//            request.getSession().setAttribute("user", githubUser);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";

        } else {
            //登入失败 请重新登入
            return "redirect:/";
        }
    }

}
