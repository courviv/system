package com.example.demo.mapper;
import com.example.demo.entity.answerdetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface answerdetailsMapper {
    List<answerdetails> getAnswerDetailsById(String test_paper_id,String student_id);
}
