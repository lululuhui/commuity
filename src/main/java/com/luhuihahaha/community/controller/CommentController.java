package com.luhuihahaha.community.controller;

import com.luhuihahaha.community.dto.CommentDTO;
import com.luhuihahaha.community.dto.CommentListDTO;
import com.luhuihahaha.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;


//    @PostMapping("/comment")
//    public void post(@RequestBody CommentDTO commentDTO){
//        System.out.println(commentDTO.toString());
//        System.out.println("我执行了");
//        commentService.insertNew(commentDTO);
//    }

    @RequestMapping("/comments")
    @ResponseBody
    public String get(){
        return "hello world";
    }

    /**
     * @param hashMap 前端传来的的信息集合
     * @param id  问题id
     */
    @PostMapping("/comment/{id}")
    @ResponseBody
    public void addComment(@RequestBody HashMap hashMap,@PathVariable(name = "id") Integer id){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentator((Integer) hashMap.get("commentator"));
        commentDTO.setContent((String) hashMap.get("content"));
        commentDTO.setParentId(Integer.valueOf(hashMap.get("parentId").toString()));
        commentDTO.setType((Integer) hashMap.get("type"));
        commentService.insertNew(commentDTO);
    }

    /**
     * @param hashMap 前端传来的的信息集合
     * @param id 父评论id
     */
    @PostMapping("/twiceComment/{id}")
    @ResponseBody
    public HashMap addTwiceComment(@PathVariable(name = "id") Integer id){
        List<CommentListDTO> coments = commentService.ListById("comment",id);
        HashMap hashMap = new HashMap();
        hashMap.put("comments",coments);
        return hashMap;
    }

}
