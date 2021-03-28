package com.test.springbootdocs.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class PostDocumentDto {
    private long id;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private UserDto user;

    private MultipartFile file;

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

    public UserDto getAuthor() {
        return user;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
