package com.luhuihahaha.community.service;

import com.luhuihahaha.community.dto.CommentDTO;
import com.luhuihahaha.community.mapper.CommentMapper;
import com.luhuihahaha.community.mapper.QuestionMapper;
import com.luhuihahaha.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;


    public void insertNew(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(commentDTO.getCommentator());
        commentMapper.insertNew(comment);
        questionMapper.addComment(commentDTO.getParentId());


    }
}
