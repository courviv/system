package com.example.demo.Service.Impl;


import com.example.demo.Service.answerListService;
import com.example.demo.entity.answer;
import com.example.demo.mapper.answerListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class answerListServiceImpl implements answerListService {
    @Autowired
    private answerListMapper answerListMapper;

    @Override
   public List<answer> getAnswerSheetsByStudentId(String student_id){
        return answerListMapper.getAnswerSheetsByStudentId(student_id);
    }
}
