package com.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * {@code @Description:}
 */
@Data
/* 以下两个注解防止栈溢出 */
@EqualsAndHashCode(exclude = {"customer"})
@ToString(exclude = {"customer"})

@Table(name = "bill")// 数据库表名
@Entity// 持久化实体类
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    // 多个账单可以属于一个消费者
    @ManyToOne
    @JoinColumn(name = "cid")// 指定外键
    private Customer customer;
}