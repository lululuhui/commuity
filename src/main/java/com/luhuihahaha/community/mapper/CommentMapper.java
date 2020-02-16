package com.luhuihahaha.community.mapper;

import com.luhuihahaha.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {


    @Insert("insert into comment(content,parent_id,type,commentator,gmt_create,gmt_modified) " +
            "values(#{comment.content},#{comment.parentId},#{comment.type},#{comment.commentator},#{comment.gmtCreate},#{comment.gmtModified}) ")
    void insertNew(@Param("comment") Comment comment);

    @Select("select * from comment where parent_id = #{id}")
    @Results({
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "likeCount",column = "like_count")
    })
    List<Comment> findByQuestionId(Integer id);

    @Update("update comment set like_count = like_count + 1 where id = #{commentId} ")
    void addLikeCount(@Param("commentId") Integer commentId);


    @Update("update comment set like_count = like_count - 1 where id = #{commentId} ")
    void reduceLikeCount(Integer commentId);



    @Select("select * from comment where id = #{commentId}")
    @Results({
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "likeCount",column = "like_count")
    })
    Comment findByCommentId(@Param("commentId") Integer commentId);


    @Select("select * from comment where parent_comment_id = #{commentId}")
    @Results({
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "likeCount",column = "like_count")
    })
    List<Comment> findByParentId(Integer id);

    @Insert("insert into comment(content,parent_id,type,commentator,gmt_create,gmt_modified,parent_comment_id) " +
            "values(#{comment.content},#{comment.parentId},#{comment.type},#{comment.commentator},#{comment.gmtCreate},#{comment.gmtModified},#{comment.parent_comm_id}) ")
    void insertTwoNew(@Param("comment") Comment comment);

    @Select("select count(1) from comment where parent_comment_id = #{id}")
    Integer countSonComment(@Param("id")Integer id);
}
