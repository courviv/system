package com.example.demo.Service.Impl;

import com.example.demo.Service.teacherService;
import com.example.demo.entity.teacher;
import com.example.demo.mapper.teacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class teacherServiceImpl implements teacherService {
    @Autowired
    private teacherMapper teacherMapper;
    @Override
    public teacher login(teacher teacher) {
        teacher teacherDB = teacherMapper.login(teacher);
        if (teacherDB != null) {
            return teacherDB;
        }
        throw  new RuntimeException("登录失败 -.-");
    }
}
