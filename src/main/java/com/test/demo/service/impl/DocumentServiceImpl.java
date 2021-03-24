package com.test.demo.service.impl;

import com.test.demo.entity.Document;
import com.test.demo.repository.DocumentRepository;
import com.test.demo.service.DocumentService;
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
