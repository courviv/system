package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class answer {

    private int question_id;
    private String test_paper_id;
    private String student_id;
    private int score;
    private String comment;
    private int status;
    private String subject;
    private String exam_id;
    private String exam_name;
    private int sumscore;
    private int psumscore;
    private Date exam_time;
}
