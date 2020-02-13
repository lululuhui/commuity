package com.luhuihahaha.community.controller;

import com.luhuihahaha.community.mapper.QuestionMapper;
import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.model.Question;
import com.luhuihahaha.community.model.User;
import com.luhuihahaha.community.service.QuestionService;
import com.luhuihahaha.community.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginUtil loginUtil;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request, Model model){
        return "publish";
    }


    @PostMapping("/publish")
    @ResponseBody
    public HashMap doPulish(@RequestBody HashMap ques, HttpServletRequest request){
        HashMap data = new HashMap();
        if(request.getSession().getAttribute("user")==null) {
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
        questionService.createOrUpdate(question,(String) ques.get("quesId"));
        data.put("info","1");
//        System.out.println(ques.get("quesId")+"----------");
        return data;
    }

    @GetMapping("/publish/{id}")
    public String edit(){
        return "publish";
    }

    @PostMapping("/getInfo")
    @ResponseBody
    public HashMap getInfo(@RequestBody HashMap map,HttpServletRequest request)  {
        String allUrl = (String) map.get("allUrl");
        System.out.println(allUrl);
        HashMap<String,Object> data = new HashMap<>();
        String[] strings = allUrl.split("/");
        if ("publish".equals(strings[strings.length-1])){
           data.put("status","0");
        }else {
            data.put("quesId",strings[strings.length-1]);
            data.put("status","1");
            Question question = questionMapper.findById(Integer.valueOf(strings[strings.length-1]));
            if (question==null) {
                request.getSession().setAttribute("errorMessage","帖子失踪了!!!");
                throw new NullPointerException();
            }
            data.put("title", question.getTitle());
            data.put("description", question.getDescription());
            data.put("tag", question.getTag());
        }
        return data;
    }



}
