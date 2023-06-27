package com.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * {@code @Description:}
 */
@Data
/* 以下两个注解防止栈溢出 */
@EqualsAndHashCode(exclude = "studentSet")
@ToString(exclude = "studentSet")

@Table(name = "course")// 数据库表名
@Entity// 持久化实体类
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 主键生成策略
    private Integer id;
    private String cname;
    
    @ManyToMany(mappedBy = "courseSet")// 放弃维护关联关系，由关联实体类的指定属性维护
    private Set<Student> studentSet;
}