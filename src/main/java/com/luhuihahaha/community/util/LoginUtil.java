package com.luhuihahaha.community.util;

import com.luhuihahaha.community.dao.UserDao;
import com.luhuihahaha.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class LoginUtil {


    @Autowired
    private UserDao userDao;

    public User checksLogin(HttpServletRequest request){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null)
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userDao.findUserByCondition("token", token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        return user;
    }


}
