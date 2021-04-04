package com.test.springbootdocs.entity;

import javax.persistence.*;

@Entity
@Table(name = "acl_entry")
public class aclEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long acl_object_identity;
    private int ace_order;
    private long sid;
    private int mask;
    private boolean granting;
    private boolean audit_success;
    private boolean audit_failure;
}
