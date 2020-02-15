package com.luhuihahaha.community.mapper;

import com.luhuihahaha.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {


    @Insert("insert into comment(content,parent_id,type,commentator,gmt_create,gmt_modified) " +
            "values(#{comment.content},#{comment.parentId},#{comment.type},#{comment.commentator},#{comment.gmtCreate},#{comment.gmtModified}) ")
    void insertNew(@Param("comment") Comment comment);
}
