package com.luhuihahaha.community.mapper;

import com.luhuihahaha.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,img) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{img}) ")
    public void insertUser(User user);

    @Select("select * from user where token = #{token}")
    public User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{creator}")
    @Results({
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
    })
    User findById(@Param("creator") Integer creator);

    @Select("select ACCOUNT_ID from user where ACCOUNT_ID = #{accountId}")
    User findByAccountId(Integer accountId);

    @Update("update user set token = #{token} where id = #{id}")
    public void updateToken(@Param("token") String token,@Param("id") Integer id);
}
