package com.example.demo.mapper;

import com.example.demo.entity.exam;
import org.apache.ibatis.annotations.Mapper;

//试卷biao
@Mapper
public interface examMapper {
    exam getExamByTestPaperId(String test_paper_id);
}
