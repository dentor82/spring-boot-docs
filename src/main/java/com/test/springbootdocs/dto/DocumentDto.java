package com.test.springbootdocs.dto;

import java.time.LocalDate;

public class DocumentDto {
    private long id;

    private String name;

    private LocalDate date;

    private String user;

    private String fileName;

    private String description;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAuthor() {
        return user;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }
}
