package com.luhuihahaha.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
    private Integer parentId;
    private int type;
    private int  commentator;
    @TableField(value = "gmt_create")
    private Long gmtCreate;
    @TableField(value = "gmt_modified")
    private Long gmtModified;
    @TableField(value = "like_count")
    private Integer likeCount;
    @TableField(value = "parent_comment_id")
    private Integer parentCommentId;

}
