package com.test.springbootdocs.dto;

import com.test.springbootdocs.enums.Mask;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.AccessControlEntry;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PermissionDto {
    private Long documentId;
    private Mask mask;
    private String principal;

    public PermissionDto() {
    }

    public PermissionDto(Mask mask, String principal) {
        this.mask = mask;
        this.principal = principal;
    }

    public PermissionDto(Long documentId) {
        this.documentId = documentId;
    }

    public static PermissionDto fromAccessControlEntry(AccessControlEntry accessControlEntry) {
        int mask = accessControlEntry.getPermission().getMask();
        PrincipalSid sid = (PrincipalSid) accessControlEntry.getSid();
        Map<Integer, Mask> collect = Arrays.stream(Mask.values())
                .collect(Collectors
                        .toMap(PermissionDto::ordinal,
                                Function.identity()));

        return new PermissionDto(collect.get(mask), sid.getPrincipal());
    }

    private static Integer ordinal(Mask mask) {
        return 1 << mask.ordinal();
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Mask getMask() {
        return mask;
    }

    public void setMask(Mask mask) {
        this.mask = mask;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
