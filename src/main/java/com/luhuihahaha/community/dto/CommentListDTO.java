package com.luhuihahaha.community.dto;

import com.luhuihahaha.community.model.User;
import lombok.Data;

@Data
public class CommentListDTO {
    private int id;
    private Integer parentId;//问题id
    private Integer type;//无用
    private Integer commentator; //
    private Long gmtCreate;//创建时间
    private Long gmtModified;//修改时间
    private Integer likeCount; //喜欢数
    private String content;     //评论内容
    private User user;         //评论人
    private Integer sonCommentSum;
    private Integer parentCommentId; //二级评论的父评论id
}
