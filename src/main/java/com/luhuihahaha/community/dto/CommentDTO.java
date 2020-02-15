package com.luhuihahaha.community.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentDTO {

    private String content;
    private Integer parentId;
    private Integer type;
    private Integer commentator;

}
