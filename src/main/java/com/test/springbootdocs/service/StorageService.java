package com.test.springbootdocs.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface StorageService {
    void store(MultipartFile file);
    File getFilePath(String fileName);
}
