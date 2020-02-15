package com.luhuihahaha.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    private Integer id;
    private String content;
    private Integer parentId;
    private int type;
    private int  commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;


}
