package com.test.springbootdocs.service.impl;

import com.test.springbootdocs.entity.Document;
import com.test.springbootdocs.repository.DocumentRepository;
import com.test.springbootdocs.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<Document> getList() {
        List<Document> retValue = new ArrayList<>();

        this.documentRepository
                .findAll()
                .forEach(retValue::add);

        return retValue;
    }
}
