package com.luhuihahaha.community.controller;

import com.luhuihahaha.community.dto.CommentListDTO;
import com.luhuihahaha.community.dto.QuestionDTO;
import com.luhuihahaha.community.model.User;
import com.luhuihahaha.community.service.CommentService;
import com.luhuihahaha.community.service.QuestionService;
import com.luhuihahaha.community.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private CommentService commentService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, HttpServletRequest request, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        if (questionDTO==null){
            request.getSession().setAttribute("errorMessage","帖子失踪了!!!");
            throw new NullPointerException();
        }

        model.addAttribute("question",questionDTO);

        questionService.addView(id);

        List<CommentListDTO> coments = commentService.ListById("question",id);

        User user = loginUtil.checksLogin(request);
        model.addAttribute("user",user);
        model.addAttribute("coments",coments);
        return "question";
    }


    @RequestMapping("/getQuestionInfo")
    @ResponseBody
    public HashMap getLoginInfo(HttpServletRequest request){
        HashMap<String,Object> data = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(user.toString());
        data.put("user",user);
        return data;

    }



    @RequestMapping("/addLikeCount")
    @ResponseBody
    public HashMap addLikeCount(@RequestBody HashMap hashMap, HttpServletRequest request){
        HashMap<String,Object> data = new HashMap<>();
        Integer commentId = Integer.valueOf((String) hashMap.get("commentId"));
        Integer likeCount = commentService.addLikeCount(commentId);
        data.put("likeCount",likeCount);
        return data;

    }

    @RequestMapping("/reduceLikeCount")
    @ResponseBody
    public HashMap reduceLikeCount(@RequestBody HashMap hashMap, HttpServletRequest request){
        HashMap<String,Object> data = new HashMap<>();
        Integer commentId = Integer.valueOf((String) hashMap.get("commentId"));
        Integer likeCount = commentService.reduceLikeCount(commentId);
        data.put("likeCount",likeCount);
        return data;

    }




}
