package com.test.springbootdocs.service.impl;

import com.test.springbootdocs.dto.DocumentDto;
import com.test.springbootdocs.dto.PostDocumentDto;
import com.test.springbootdocs.entity.Document;
import com.test.springbootdocs.entity.User;
import com.test.springbootdocs.repository.DocumentRepository;
import com.test.springbootdocs.repository.UserRepository;
import com.test.springbootdocs.service.DocumentService;
import com.test.springbootdocs.utils.ObjectMapperUtil;
import org.springframework.data.domain.Sort;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final PermissionService permissionService;

    public DocumentServiceImpl(DocumentRepository documentRepository, UserRepository userRepository, PermissionService permissionService) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.permissionService = permissionService;
        if (!this.documentRepository.existsById(1L)) {
            User admin = this.userRepository.findById(1L).get();
            Document document = new Document("Название", LocalDate.now(), admin, "test.pdf", "Описание");
            this.documentRepository.save(document);
            Authentication request = new UsernamePasswordAuthenticationToken("admin", "admin", List.of(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(request);
            this.permissionService.addPermissionForUser(document.getClass(), document.getId(),
                    BasePermission.READ, document.getUser().getUsername());
            this.permissionService.addPermissionForUser(document.getClass(), document.getId(),
                    BasePermission.WRITE, document.getUser().getUsername());
        }
    }

    @Override
    public List<DocumentDto> getList(String userName, String... columns) {
        Sort sort = Sort.by(columns);
        return new ArrayList<>(
                ObjectMapperUtil
                        .mapAll(this.documentRepository
                                .findAllDocumentByAccess(sort, userName, BasePermission.READ.getMask()), DocumentDto.class)
        );
    }

    @Override
    public void save(PostDocumentDto document) {
        Document newDocument = ObjectMapperUtil.map(document, Document.class);
        newDocument.setFileName(document.getFile().getOriginalFilename());
        this.userRepository
                .findById(document.getAuthor().getId())
                .ifPresent(newDocument::setUser);
        this.documentRepository.save(newDocument);
        this.permissionService.addPermissionForUser(newDocument.getClass(), newDocument.getId(),
                BasePermission.READ, newDocument.getUser().getUsername());
        this.permissionService.addPermissionForUser(newDocument.getClass(), newDocument.getId(),
                BasePermission.WRITE, newDocument.getUser().getUsername());
    }
}
