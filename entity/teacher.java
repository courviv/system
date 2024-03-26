package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class teacher {
    private String teacher_id;
    private String username;
    private String phone_number;
    private String email;
    private String subject;
    private String school_id;
    private String password;

}
