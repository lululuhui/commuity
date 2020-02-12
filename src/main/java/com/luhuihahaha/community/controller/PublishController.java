package com.luhuihahaha.community.controller;

import com.luhuihahaha.community.mapper.QuestionMapper;
import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.model.Question;
import com.luhuihahaha.community.model.User;
import com.luhuihahaha.community.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginUtil loginUtil;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request){
/*        Cookie[] cookies = request.getCookies();
        if (cookies!=null)
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }*/
//        loginUtil.checkLogin(request);
        return "publish";
    }


    @PostMapping("/publish")
    @ResponseBody
    public HashMap doPulish(@RequestBody HashMap ques, HttpServletRequest request){
        HashMap data = new HashMap();
        if(request.getSession().getAttribute("user")==null) {
            data.put("message","请先登入");
            data.put("info","0");
            return data;
        }


        User user = (User) request.getSession().getAttribute("user");

        Question question = new Question();
        question.setTag((String) ques.get("tag"));
        question.setDescription((String) ques.get("description"));
        question.setTitle((String) ques.get("title"));
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        data.put("message","发布成功");
        data.put("info","1");
        return data;
    }

}
