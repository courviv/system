package com.example.demo.mapper;

import com.example.demo.entity.examinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface examinfoMapper {
    examinfo getExamInfoByExamName(String exam_name);
}
