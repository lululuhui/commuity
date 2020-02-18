package com.luhuihahaha.community.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luhuihahaha.community.mapper.QuestionsMapper;
import com.luhuihahaha.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {

    @Autowired
    private QuestionsMapper questionsMapper;

    public void insertQuestion(Question question) {
        questionsMapper.insert(question);
    }

    public List<Question> listPage(Integer offset, Integer size) {
        Page<Question> questionPage = new Page<>(offset, size);
        IPage<Question> questionIPage = questionsMapper.selectPage(questionPage, new QueryWrapper<>());
        return questionIPage.getRecords();
    }

    public List<Question> listPage(Integer userId, Integer page, Integer size) {
        Page<Question> questionPage = new Page<>(page, size);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", userId);
        IPage<Question> questionIPage = questionsMapper.selectPage(questionPage, queryWrapper);
        return questionIPage.getRecords();
    }

    public Integer countQuestion(String condition, Integer userId) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if ("userId".equals(condition))
            queryWrapper.eq("creator", userId);
        return questionsMapper.selectCount(queryWrapper);
    }

    public Question findQuestionById(Integer id) {
        return questionsMapper.selectById(id);
    }

    public void updateQuestion(Integer id, Question question) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        questionsMapper.update(question, queryWrapper);
    }

    public void addQuestionCount(String condition, Integer id) {
        Question question = questionsMapper.selectById(id);
        UpdateWrapper<Question> questionUpdateWrapper = new UpdateWrapper<>();
        if ("view".equals(condition))
            questionUpdateWrapper.set("view_count", question.getViewCount() + 1)
                    .eq("id", id);
        else if ("comment".equals(condition))
            questionUpdateWrapper.set("comment_count", question.getCommentCount() + 1)
                    .eq("id", id);
        questionsMapper.update(null, questionUpdateWrapper);
    }

    public List<Question> searchQuestion(String str, Integer page, Integer size) {
        Page<Question> questionPage = new Page<>(page, size);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", str).like("description", str).like("tag", str);
        IPage<Question> questionsIPage = questionsMapper.selectPage(questionPage, queryWrapper);
        return questionsIPage.getRecords();
    }

    public Integer countSearchQuestion(String str) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", str).like("description", str).like("tag", str);
        return questionsMapper.selectCount(queryWrapper);
    }


}
