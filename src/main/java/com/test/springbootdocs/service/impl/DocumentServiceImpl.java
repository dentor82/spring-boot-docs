package com.test.springbootdocs.service.impl;

import com.test.springbootdocs.dto.DocumentDto;
import com.test.springbootdocs.entity.Document;
import com.test.springbootdocs.entity.User;
import com.test.springbootdocs.repository.DocumentRepository;
import com.test.springbootdocs.repository.UserRepository;
import com.test.springbootdocs.service.DocumentService;
import com.test.springbootdocs.utils.ObjectMapperUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        if (!this.documentRepository.existsById(1L)) {
            User admin = this.userRepository.findById(1L).get();
            this.documentRepository.save(new Document("Название", LocalDate.now(), admin, "test.pdf", "Описание"));
        }
    }

    @Override
    public List<DocumentDto> getList() {
        List<DocumentDto> retValue = new ArrayList<>();

        ObjectMapperUtil.mapAll(this.documentRepository
                .findAll(), DocumentDto.class)
                .forEach(retValue::add);

        return retValue;
    }
}
