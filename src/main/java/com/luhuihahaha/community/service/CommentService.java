package com.luhuihahaha.community.service;

import com.luhuihahaha.community.dto.CommentDTO;
import com.luhuihahaha.community.dto.CommentListDTO;
import com.luhuihahaha.community.mapper.CommentMapper;
import com.luhuihahaha.community.mapper.QuestionMapper;
import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public  Integer addLikeCount(Integer commentId) {
        commentMapper.addLikeCount(commentId);
        Comment comment = commentMapper.findByCommentId(commentId);
        return comment.getLikeCount();
    }
    public Integer reduceLikeCount(Integer commentId) {
        commentMapper.reduceLikeCount(commentId);
        Comment comment = commentMapper.findByCommentId(commentId);
        return comment.getLikeCount();
    }

    public void insertNew(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(commentDTO.getCommentator());
        comment.setParent_comm_id(commentDTO.getParent_comm_id());
        if (comment.getParent_comm_id()==null)
            commentMapper.insertNew(comment);
         else
            commentMapper.insertTwoNew(comment);

        questionMapper.addComment(commentDTO.getParentId());


    }


    public List<CommentListDTO> ListById(String str,Integer id) {
        List<CommentListDTO> commentListDTOList = new ArrayList<>();
        List<Comment> commentList = null;
        if ("question".equals(str)){
            commentList = commentMapper.findByQuestionId(id);
        }else if ("comment".equals(str)) {
            commentList = commentMapper.findByParentId(id);
        }
        for (Comment comment: commentList) {
            CommentListDTO commentListDTO = new CommentListDTO();
            commentListDTO.setCommentator(comment.getCommentator());
            commentListDTO.setContent(comment.getContent());
            commentListDTO.setGmtCreate(comment.getGmtCreate());
            commentListDTO.setGmtModified(comment.getGmtModified());
            commentListDTO.setParentId(comment.getParentId());
            commentListDTO.setLikeCount(comment.getLikeCount());
            commentListDTO.setUser(userMapper.findById(commentListDTO.getCommentator()));
            commentListDTO.setId(comment.getId());
            commentListDTO.setSonCommentSum(commentMapper.countSonComment(comment.getId()));
            commentListDTOList.add(commentListDTO);
        }
        return commentListDTOList;
    }



}
