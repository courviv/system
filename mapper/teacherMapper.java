package com.example.demo.mapper;
import com.example.demo.entity.teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface teacherMapper {
    //教师登录
    teacher login(teacher teacher);

}