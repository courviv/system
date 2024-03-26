package com.example.demo.Service;

import com.example.demo.entity.student;
import org.springframework.stereotype.Service;

@Service
public interface studentService {
     student login(student student);
}
