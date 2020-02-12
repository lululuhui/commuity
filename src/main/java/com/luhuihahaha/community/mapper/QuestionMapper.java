package com.luhuihahaha.community.mapper;

import com.luhuihahaha.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into questions(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from questions limit #{offset},#{size}")
    @Results({
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
            @Result(property = "commentCount",column = "comment_count"),
            @Result(property = "viewCount",column = "view_count"),
            @Result(property = "likeCount",column = "like_count")
    })
    public List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from questions")
    public Integer count();
}
