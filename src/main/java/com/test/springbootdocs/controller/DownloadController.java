package com.test.springbootdocs.controller;

import com.test.springbootdocs.entity.Document;
import com.test.springbootdocs.repository.DocumentRepository;
import com.test.springbootdocs.service.StorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Controller
public class DownloadController {
    private final DocumentRepository documentRepository;
    private final StorageService storageService;

    public DownloadController(DocumentRepository documentRepository, StorageService storageService) {
        this.documentRepository = documentRepository;
        this.storageService = storageService;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewFile(@RequestParam Long documentId) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Document> document = Optional.ofNullable(documentRepository.findByIdAccess(documentId, authentication.getName(), BasePermission.READ.getMask()));
        return document.map(Document::getFileName)
                .map(this::viewResponse)
                .orElseGet(this::viewNotFound);
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@RequestParam Long documentId) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Document> document = Optional.ofNullable(documentRepository.findByIdAccess(documentId, authentication.getName(), BasePermission.READ.getMask()));
        return document.map(Document::getFileName)
                .map(this::downloadResponse)
                .orElseGet(this::viewNotFound);
    }

    private ResponseEntity viewNotFound() {
        return ResponseEntity.noContent()
                .build();
    }

    private ResponseEntity<Resource> downloadResponse(String fileName) {
        File file = storageService.getFilePath(fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            ByteArrayResource resource = new ByteArrayResource(fileInputStream.readAllBytes());

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
        }

        return viewNotFound();
    }

    private ResponseEntity<byte[]> viewResponse(String fileName) {
        File file = storageService.getFilePath(fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String extension = StringUtils.getFilenameExtension(file.getName());

            return ResponseEntity
                    .ok()
                    .contentLength(fileInputStream.available())
                    .contentType(
                            MediaType.parseMediaType("application/" + extension))
                    .body(fileInputStream.readAllBytes());
        } catch (IOException e) {
        }

        return viewNotFound();
    }
}
