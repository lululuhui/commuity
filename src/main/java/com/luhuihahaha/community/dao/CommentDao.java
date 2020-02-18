package com.luhuihahaha.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.luhuihahaha.community.mapper.CommentsMapper;
import com.luhuihahaha.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao {

    @Autowired
    private CommentsMapper commentsMapper;

    public void insertComment(Comment comment) {
        commentsMapper.insert(comment);
    }

    public List<Comment> findByQuestionId(Integer id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Comment> commentList = commentsMapper.selectList(wrapper);
        return commentList;
    }

    public void changeCount(boolean type, Integer commentId) {
        Comment comment = commentsMapper.selectById(commentId);
        UpdateWrapper<Comment> commentUpdateWrapper = new UpdateWrapper<>();
        if (type) commentUpdateWrapper.set("like_count", comment.getLikeCount() + 1)
                .eq("id", commentId);
        else commentUpdateWrapper.set("like_count", comment.getLikeCount() - 1)
                .eq("id", commentId);
        commentsMapper.update(null, commentUpdateWrapper);
    }

    public Comment findByCommentId(Integer id) {
        return commentsMapper.selectById(id);
    }

    public List<Comment> findByParentId(Integer id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_comment_id", id);
        List<Comment> commentList = commentsMapper.selectList(wrapper);
        return commentList;
    }

    public Integer countSonComment(Integer id) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("parent_comment_id", id);
        return commentsMapper.selectCount(commentQueryWrapper);
    }

}
