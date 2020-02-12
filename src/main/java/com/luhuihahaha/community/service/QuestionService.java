package com.luhuihahaha.community.service;

import com.luhuihahaha.community.dto.PageDTO;
import com.luhuihahaha.community.dto.QuestionDTO;
import com.luhuihahaha.community.mapper.QuestionMapper;
import com.luhuihahaha.community.mapper.UserMapper;
import com.luhuihahaha.community.model.Question;
import com.luhuihahaha.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PageDTO getQuestion(Integer page, Integer size) {
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        pageDTO.setQuestionDTOS(questionDTOS);

        Integer totalCount = questionMapper.count();

        pageDTO.setPagintion(totalCount,page,size);
        return pageDTO;
    }

    public PageDTO list(Integer userId, Integer page, Integer size) {

        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.lists(userId,offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        pageDTO.setQuestionDTOS(questionDTOS);

        Integer totalCount = questionMapper.countByUserId(userId);

        pageDTO.setPagintion(totalCount,page,size);
        return pageDTO;

    }
}
