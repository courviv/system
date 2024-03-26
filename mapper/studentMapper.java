package com.example.demo.mapper;

import com.example.demo.entity.student;
import org.apache.ibatis.annotations.Mapper;
//@Repository

@Mapper
public interface studentMapper {
    student login(student student);
}
