package com.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * {@code @description:}
 */
@Data
/* 以下两个注解防止栈溢出 */
@EqualsAndHashCode(exclude = "courseSet")
@ToString(exclude = "courseSet")

@Table(name = "student")// 数据库表名
@Entity// 持久化实体类
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 主键生成策略
    private Integer id;
    private String sname;
    
    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courseSet;
}