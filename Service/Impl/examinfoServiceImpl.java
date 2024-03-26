package com.example.demo.Service.Impl;

import com.example.demo.Service.examinfoService;
import com.example.demo.entity.examinfo;
import com.example.demo.mapper.examinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class examinfoServiceImpl implements examinfoService {
    @Autowired
    private examinfoMapper examinfoMapper;
    @Override
    public examinfo getExamInfoByExamName(String exam_name) {
        return examinfoMapper.getExamInfoByExamName(exam_name);
    }
}