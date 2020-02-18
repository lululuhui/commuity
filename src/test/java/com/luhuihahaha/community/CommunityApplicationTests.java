package com.luhuihahaha.community;


import com.luhuihahaha.community.dao.CommentDao;
import com.luhuihahaha.community.dao.QuestionDao;
import com.luhuihahaha.community.dao.UserDao;
import com.luhuihahaha.community.mapper.CommentsMapper;
import com.luhuihahaha.community.model.Comment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommunityApplicationTests {


    @Autowired
    private CommentDao commentDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Comment> userList = commentsMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        Comment comment = new Comment();
        comment.setParentCommentId(1);
        comment.setCommentator(1);
        comment.setGmtModified(1581689835802L);
        comment.setGmtCreate(1581689835800L);
        comment.setParentId(-1);
        comment.setLikeCount(10);
        comment.setType(1);
        comment.setContent("mybatisPlusTest");

        int insert = commentsMapper.insert(comment);
        System.out.println(insert);
    }

    @Test
    public void testUpdate() {
        commentDao.changeCount(false, 1);
    }

    @Test
    public void testList() {
        List<Comment> byQuestionId = commentDao.findByQuestionId(1);
        byQuestionId.forEach(System.out::println);
    }

    @Test
    public void testFindOne() {
        System.out.println(commentDao.findByCommentId(1));
    }

    @Test
    public void testall() {
//        commentDao.findByParentId(1).forEach(System.out::println);
//        System.out.println(commentDao.countSonComment(1));
//        Question question = new Question();
//        question.setGmtCreate(System.currentTimeMillis());
//        question.setGmtModified(System.currentTimeMillis());
//        question.setCreator(1);
//        question.setTitle("spring");
//        question.setTag("aaa");
//        question.setDescription("测试测试");
//        question.setLikeCount(100);
//        question.setViewCount(121);
//        question.setCommentCount(0);
//        questionDao.insertQuestion(question);
//        questionDao.listPage(1,5).forEach(System.out::println);

//        System.out.println(questionDao.countQuestion("userId",1));

//        userDao.updateUserToken("e5a59adc-1be8-4c53-9398-ab16e8ea6ca1",1);

        questionDao.addQuestionCount("comment", 24);



    }

    @Test
    public void abc() {
        int count = 0;
        for (int i = 1; i <= 300; i++) {
            if ((i % 3 == 0 || i % 5 == 0) && i % 7 != 0)
                count++;
        }
        System.out.println(count);
    }
}
