package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class examinfo {

    private String exam_id;
    private String exam_name;
    private String exam_type;
    private String exam_status;
    private String exam_time;
    private String administrator_id;


}
