package com.example.demo.Service;

import com.example.demo.entity.answerdetails;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface answerdetailService {
    List<PageInfo<Map<String, Object>>> getAnswerDetailsByTestPaperId(String test_paper_id, String student_id,int pageNum, int pageSize);
    PageInfo<answerdetails> getAnswerDetailsById(String test_paper_id, String student_id,int pageSum,int pageSize);
}
