package com.test.springbootdocs.enums;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

public enum Mask {
    READ (BasePermission.READ),
    WRITE (BasePermission.WRITE),
    CREATE (BasePermission.CREATE),
    DELETE (BasePermission.DELETE);

    private Permission permission;

    Mask(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }
}
