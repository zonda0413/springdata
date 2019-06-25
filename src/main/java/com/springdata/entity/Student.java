package com.springdata.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Accessors(chain = true)
@Document
public class Student {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private int age;
    @Field(value = "a_time")
    private Date addTime;
}
