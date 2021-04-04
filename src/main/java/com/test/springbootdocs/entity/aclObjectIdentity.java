package com.test.springbootdocs.entity;

import javax.persistence.*;

@Entity
@Table(name = "acl_object_identity")
public class aclObjectIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long object_id_class;
    private long object_id_identity;
    private Long parent_object;
    private Long owner_sid;
    private boolean entries_inheriting;
}
