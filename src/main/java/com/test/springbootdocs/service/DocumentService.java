package com.test.springbootdocs.service;

import com.test.springbootdocs.dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    List<DocumentDto> getList();
}
