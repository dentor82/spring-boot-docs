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
    private String file;

    @Lob
    private String description;

    public Document() {

    }

    public Document(String name, LocalDate date, User user, String fileName, String description) {
        this.name = name;
        this.date = date;
        this.user = user;
        this.file = fileName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return file;
    }

    public void setFileName(String fileName) {
        this.file = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
