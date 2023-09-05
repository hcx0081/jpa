package com.hibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

/**
 * @description:
 */
@Data
/* 以下两个注解防止栈溢出 */
@EqualsAndHashCode(exclude = "billSet")
@ToString(exclude = "billSet")
public class Customer {
    private Integer id;
    private String name;

    // 一个消费者有多个账单
    private Set<Bill> billSet;
}