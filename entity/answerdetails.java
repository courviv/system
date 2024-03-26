package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Param;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class answerdetails {
    private String test_paper_id;
    private String question_id;
    private String question;
    private String answer;
    private int pscore;
    private String student_id;
    private int score;
    private String comment;
    private boolean wrong_book;
}
