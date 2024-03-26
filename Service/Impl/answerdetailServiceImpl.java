package com.example.demo.Service.Impl;

import com.example.demo.Service.answerdetailService;
import com.example.demo.entity.answer;
import com.example.demo.entity.answerdetails;
import com.example.demo.entity.question;
import com.example.demo.mapper.answerListMapper;
import com.example.demo.mapper.answerdetailsMapper;
import com.example.demo.mapper.questionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class answerdetailServiceImpl implements answerdetailService {
    @Autowired
    private answerListMapper answerListMapper;
    @Autowired
    private questionMapper questionMapper;
    @Override
    public List<PageInfo<Map<String, Object>>> getAnswerDetailsByTestPaperId(String test_paper_id,String student_id,int pageNum, int pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        List<answer> answers = answerListMapper.getAnswerSheetsByTestPaperId(test_paper_id,student_id);
        List<question> questions = questionMapper.getQuestionsByTestPaperId(test_paper_id);

        List<PageInfo<Map<String, Object>>> pageInfos = new ArrayList<>();
        // 合并答卷表和试题表数据
//        List<Map<String, Object>> mergedList = new ArrayList<>();
        for (int i = 0; i < Math.min(answers.size(), questions.size()); i++) {
            Map<String, Object> mergedDetails = new HashMap<>();
            answer answer = answers.get(i);
            question question = questions.get(i);
            mergedDetails.put("question_id", question.getQuestion_id());
            mergedDetails.put("question", question.getQuestion());
            mergedDetails.put("answer", question.getAnswer());
            mergedDetails.put("pscore", question.getPscore());
            mergedDetails.put("score", answer.getScore());
            mergedDetails.put("comment", answer.getComment());
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(Arrays.asList(mergedDetails));
            pageInfos.add(pageInfo);
//            mergedList.add(mergedDetails);
        }

        // 封装成 PageInfo 对象返回
//        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(mergedList);
        return pageInfos;
    }
    @Autowired
    private answerdetailsMapper answerdetailsMapper;
    @Override
    public  PageInfo<answerdetails> getAnswerDetailsById(String test_paper_id, String student_id,int pageNum,int pageSize) {
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 查询数据
        List<answerdetails> answerList = answerdetailsMapper.getAnswerDetailsById(test_paper_id, student_id);
        PageInfo<answerdetails> pageInfo = new PageInfo<>(answerList);

        return pageInfo;
    }
}
