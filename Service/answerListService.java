package com.example.demo.Service;

import com.example.demo.entity.answer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface answerListService {
    List<answer> getAnswerSheetsByStudentId(String student_id);
}
