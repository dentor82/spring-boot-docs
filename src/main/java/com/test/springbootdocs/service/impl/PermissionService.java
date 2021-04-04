package com.test.springbootdocs.service.impl;

import com.test.springbootdocs.dto.PermissionDto;
import com.test.springbootdocs.entity.Document;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionService {

    final private MutableAclService aclService;
    final private PlatformTransactionManager transactionManager;

    public PermissionService(MutableAclService aclService, PlatformTransactionManager transactionManager) {
        this.aclService = aclService;
        this.transactionManager = transactionManager;
    }

    public void addPermissionForUser(Class objectClass, Long objectId, Permission permission, String username) {
        final Sid sid = new PrincipalSid(username);
        addPermissionForSid(objectClass, objectId, permission, sid);
    }

    public void addPermissionForAuthority(Class objectClass, Long objectId, Permission permission, String authority) {
        final Sid sid = new GrantedAuthoritySid(authority);
        addPermissionForSid(objectClass, objectId, permission, sid);
    }

    private void addPermissionForSid(Class objectClass, Long objectId, Permission permission, Sid sid) {
        final TransactionTemplate tt = new TransactionTemplate(transactionManager);

        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                final ObjectIdentity oi = new ObjectIdentityImpl(objectClass, objectId);

                MutableAcl acl = null;
                try {
                    acl = (MutableAcl) aclService.readAclById(oi);
                } catch (final NotFoundException nfe) {
                    acl = aclService.createAcl(oi);
                }

                acl.insertAce(acl.getEntries()
                        .size(), permission, sid, true);
                aclService.updateAcl(acl);
            }
        });
    }

    public List<PermissionDto> getListPermissions(Class objectClass, Long objectId) {
        final ObjectIdentity oi = new ObjectIdentityImpl(objectClass, objectId);
        Acl acl = aclService.readAclById(oi);
        List<AccessControlEntry> entries = acl.getEntries();

        return entries
                .stream()
                .map(PermissionDto::fromAccessControlEntry)
                .collect(Collectors.toList());
    }
}
