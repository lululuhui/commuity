package com.luhuihahaha.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.luhuihahaha.community.mapper.UsersMapper;
import com.luhuihahaha.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private UsersMapper usersMapper;

    public void insertUser(User user) {
        usersMapper.insert(user);
    }

    public User findUserByCondition(String condition, String content) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if ("token".equals(condition))
            queryWrapper.eq("token", content);
        if ("accountId".equals(condition))
            queryWrapper.eq("account_id", content);

        return usersMapper.selectOne(queryWrapper);
    }

    public User findUserById(Integer creator) {
        return usersMapper.selectById(creator);
    }

    public void updateUserToken(String token, Integer id) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", id).set("token", token);
        usersMapper.update(null, userUpdateWrapper);
    }
}
