package com.test.springbootdocs.entity;

import javax.persistence.*;

@Entity
@Table(name = "acl_class")
public class aclClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "class")
    private String className;
}
