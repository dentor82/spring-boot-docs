package com.test.springbootdocs.entity;

import javax.persistence.*;

@Entity
@Table(name = "acl_sid")
public class aclSid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean principal;
    private String sid;
}
