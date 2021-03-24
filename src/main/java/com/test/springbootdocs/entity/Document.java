package com.test.springbootdocs.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDate date;

    @ManyToOne
    private User user;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    private String description;
}
