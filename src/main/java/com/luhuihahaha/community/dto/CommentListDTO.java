package com.luhuihahaha.community.dto;

import com.luhuihahaha.community.model.User;
import lombok.Data;

@Data
public class CommentListDTO {
    private int id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private User user;
    private Integer parentCommentId;
}
