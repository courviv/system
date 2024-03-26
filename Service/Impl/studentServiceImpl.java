package com.example.demo.Service.Impl;

import com.example.demo.Service.studentService;
import com.example.demo.entity.student;
import com.example.demo.mapper.studentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class studentServiceImpl implements studentService {
    @Autowired
    private studentMapper studentMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public student login(student student) {
        student studentDB = studentMapper.login(student);
        if(studentDB!=null){
            return studentDB;
        }
        throw  new RuntimeException("登录失败 -.-");
    }
}
