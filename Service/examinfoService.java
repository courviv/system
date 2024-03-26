package com.example.demo.Service;

import com.example.demo.entity.examinfo;
import org.springframework.stereotype.Service;

@Service
public interface examinfoService {
    examinfo getExamInfoByExamName(String exam_name);
}
