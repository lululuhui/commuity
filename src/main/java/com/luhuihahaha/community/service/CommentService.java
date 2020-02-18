package com.luhuihahaha.community.service;

import com.luhuihahaha.community.dao.CommentDao;
import com.luhuihahaha.community.dao.QuestionDao;
import com.luhuihahaha.community.dao.UserDao;
import com.luhuihahaha.community.dto.CommentDTO;
import com.luhuihahaha.community.dto.CommentListDTO;
import com.luhuihahaha.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentDao commentDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    public  Integer addLikeCount(Integer commentId) {
        commentDao.changeCount(true, commentId);
        Comment comment = commentDao.findByCommentId(commentId);
        return comment.getLikeCount();
    }
    public Integer reduceLikeCount(Integer commentId) {
        commentDao.changeCount(false, commentId);
        Comment comment = commentDao.findByCommentId(commentId);
        return comment.getLikeCount();
    }

    public void insertNew(boolean type, CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(commentDTO.getCommentator());
        comment.setParentCommentId(commentDTO.getParent_comm_id());
        commentDao.insertComment(comment);
        if (type)
            questionDao.addQuestionCount("comment", commentDTO.getParentId());


    }


    public List<CommentListDTO> ListById(String str,Integer id) {
        List<CommentListDTO> commentListDTOList = new ArrayList<>();
        List<Comment> commentList = null;
        if ("question".equals(str)){
            commentList = commentDao.findByQuestionId(id);
        }else if ("comment".equals(str)) {
            commentList = commentDao.findByParentId(id);
        }
        for (Comment comment: commentList) {
            CommentListDTO commentListDTO = new CommentListDTO();
            commentListDTO.setCommentator(comment.getCommentator());
            commentListDTO.setContent(comment.getContent());
            commentListDTO.setGmtCreate(comment.getGmtCreate());
            commentListDTO.setGmtModified(comment.getGmtModified());
            commentListDTO.setParentId(comment.getParentId());
            commentListDTO.setLikeCount(comment.getLikeCount());
            commentListDTO.setUser(userDao.findUserById(commentListDTO.getCommentator()));
            commentListDTO.setId(comment.getId());
            commentListDTO.setSonCommentSum(commentDao.countSonComment(comment.getId()));
            commentListDTOList.add(commentListDTO);
        }
        return commentListDTOList;
    }



}
