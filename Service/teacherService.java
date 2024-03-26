package com.example.demo.Service;

import com.example.demo.entity.teacher;
import org.springframework.stereotype.Service;

@Service
public interface teacherService {
    teacher login(teacher teacher);
}
