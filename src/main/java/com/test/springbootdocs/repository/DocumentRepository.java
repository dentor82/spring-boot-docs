package com.test.springbootdocs.repository;

import com.test.springbootdocs.entity.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Override
    @PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, admin)")
    List<Document> findAll(Sort sort);
}
