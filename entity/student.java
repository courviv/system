package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class student {
    private String student_id;
    private String username;
    private String password;
    private String phone_number;
    private String email;
    private String class_id;
}
