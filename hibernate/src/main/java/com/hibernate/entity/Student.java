package com.hibernate.entity;

import lombok.Data;

import java.util.Set;

/**
 * @description:
 */
@Data
public class Student {
    private Integer id;
    private String sname;
    private Set<Course> courseSet;
}