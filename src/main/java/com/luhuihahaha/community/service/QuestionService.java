package com.luhuihahaha.community.service;

import com.luhuihahaha.community.dao.QuestionDao;
import com.luhuihahaha.community.dao.UserDao;
import com.luhuihahaha.community.dto.PageDTO;
import com.luhuihahaha.community.dto.QuestionDTO;
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
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    public PageDTO getQuestion(Integer page, Integer size) {

//        Integer offset = size * (page - 1);

        List<Question> questions = questionDao.listPage(page, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        for (Question question : questions) {
            User user = userDao.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        pageDTO.setQuestionDTOS(questionDTOS);

        Integer totalCount = questionDao.countQuestion("null", 0);

        pageDTO.setPagintion(totalCount,page,size);
        return pageDTO;
    }

    public PageDTO list(Integer userId, Integer page, Integer size) {

//        Integer offset = size * (page - 1);

        List<Question> questions = questionDao.listPage(userId, page, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        for (Question question : questions) {
            User user = userDao.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        pageDTO.setQuestionDTOS(questionDTOS);

        Integer totalCount = questionDao.countQuestion("userId", userId);

        pageDTO.setPagintion(totalCount,page,size);
        return pageDTO;

    }

    public PageDTO search(String str, Integer page, Integer size) {

//        Integer offset = size * (page - 1);

        List<Question> questions = questionDao.searchQuestion(str, page, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        for (Question question : questions) {
            User user = userDao.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        pageDTO.setQuestionDTOS(questionDTOS);

        Integer totalCount = questionDao.countSearchQuestion(str);

        pageDTO.setPagintion(totalCount,page,size);
        return pageDTO;

    }

    public QuestionDTO getById(Integer id) {
        Question question = questionDao.findQuestionById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(userDao.findUserById(questionDTO.getCreator()));
        return questionDTO;
    }

    public void createOrUpdate(Question question,String quesId) {

       if (quesId == null){
           questionDao.insertQuestion(question);
       }else {
           questionDao.updateQuestion(Integer.valueOf(quesId), question);
       }



    }

    public void addView(Integer id) {
        questionDao.addQuestionCount("view", id);
    }

}
