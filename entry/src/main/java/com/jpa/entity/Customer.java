package com.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @Description:
 */
@Data
/* 以下两个注解防止栈溢出 */
@EqualsAndHashCode(exclude = "billSet")
@ToString(exclude = "billSet")

@Table(name = "customer")// 数据库表名
@Entity// 持久化实体类
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    // 一个消费者有多个账单
    @OneToMany(mappedBy = "customer")// 指定维护关联关系的关联目标属性，且不能标注@JoinColumn
    /*
     * 单向一对多必须为多端设置外键，不设置外键则会创建一张中间表，因为多端没有注解，不确定外键是什么
     * 双向一对多指定外键，因为需要与多端的外键一致
     *  */
    // @JoinColumn(name = "cid")
    private Set<Bill> billSet;
}