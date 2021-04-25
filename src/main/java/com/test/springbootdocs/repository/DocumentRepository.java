package com.test.springbootdocs.repository;

import com.test.springbootdocs.entity.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.acls.model.Permission;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select d from Document d left join AclEntry acl on d.id = acl.acl_object_identity left join AclSid sid on acl.sid = sid.id where sid.sid = :user and acl.mask <= :mask")
    List<Document> findAllDocumentByAccess(Sort sort, String user, int mask);
    @Query("select d from Document d left join AclEntry acl on d.id = acl.acl_object_identity left join AclSid sid on acl.sid = sid.id where d.id = :id and sid.sid = :user and acl.mask <= :mask")
    Document findByIdAccess(Long id, String user, int mask);
}
