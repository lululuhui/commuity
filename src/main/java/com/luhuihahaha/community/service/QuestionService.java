package com.luhuihahaha.community.service;

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

    public List<QuestionDTO> getQuestion() {

        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }
}
