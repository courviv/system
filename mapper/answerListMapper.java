package com.example.demo.mapper;

import com.example.demo.entity.answer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper
public interface answerListMapper {
    List<answer> getAnswerSheetsByStudentId(String student_id);
    List<answer> getAnswerSheetsByTestPaperId (String test_paper_id,String student_id);
}
