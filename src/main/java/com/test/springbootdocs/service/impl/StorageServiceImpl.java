package com.test.springbootdocs.service.impl;

import com.test.springbootdocs.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${storage.files.path}")
    private String path;

    @Override
    public void store(MultipartFile file) {
        Path filePath = Path.of(path, file.getOriginalFilename());
        File newFile = filePath.toFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getFilePath(String fileName) {
        Path filePath = Path.of(path, fileName);
        return filePath.toFile();
    }
}
