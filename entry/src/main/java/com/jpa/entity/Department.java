package com.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Description:
 */
@Data
/* 以下两个注解防止栈溢出 */
@EqualsAndHashCode(exclude = "manager")
@ToString(exclude = "manager")

@Table(name = "department")// 数据库表名
@Entity// 持久化实体类
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "d_name")
    private String dName;
    
    @OneToOne
    @JoinColumn(name = "mid", unique = true)// 指定外键，维护关联关系
    private Manager manager;
}