package com.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * {@code @description:}
 */
@Data
@Table(name = "person")// 数据库表名
@Entity// 持久化实体类
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 主键生成策略
    private Integer id;
    private String name;
    private Integer age;
}