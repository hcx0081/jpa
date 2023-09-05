package com.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * {@code @description:}
 */
@Data
@Entity
@Table
public class Cat {
    @Id
    @SequenceGenerator(name = "cat_sequence", sequenceName = "cat_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String firstName;
    
    
    @Basic
    private String lastName;
    
    private Integer age;
}