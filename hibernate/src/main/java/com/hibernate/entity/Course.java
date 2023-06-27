package com.hibernate.entity;

import lombok.Data;

import java.util.Set;

/**
 * @Description:
 */
@Data
public class Course {
    private Integer id;
    private String cname;
    private Set<Student> studentSet;
}