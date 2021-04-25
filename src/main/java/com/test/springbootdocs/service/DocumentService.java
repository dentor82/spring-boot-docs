package com.test.springbootdocs.service;

import com.test.springbootdocs.dto.DocumentDto;
import com.test.springbootdocs.dto.PostDocumentDto;

import java.util.List;

public interface DocumentService {
    List<DocumentDto> getList(String userName, String... columns);

    void save(PostDocumentDto document);
}
