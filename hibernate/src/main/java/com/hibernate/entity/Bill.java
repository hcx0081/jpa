package com.hibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description:
 */
@Data
/* 以下两个注解防止栈溢出 */
@EqualsAndHashCode(exclude = {"customer"})
@ToString(exclude = {"customer"})
public class Bill {
    private Integer id;
    private String name;

    // 多个账单可以属于一个消费者
    private Customer customer;
}