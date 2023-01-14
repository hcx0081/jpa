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
@EqualsAndHashCode(exclude = "department")
@ToString(exclude = "department")

@Table(name = "manager")// 数据库表名
@Entity// 持久化实体类
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "m_name")
    private String mName;
    
    @OneToOne(mappedBy = "manager")
    private Department department;
}