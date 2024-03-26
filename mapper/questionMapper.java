package com.example.demo.mapper;

import com.example.demo.entity.question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//
@Mapper
public interface questionMapper {
    List<question> getQuestionsByTestPaperId(String test_paper_id);
}
