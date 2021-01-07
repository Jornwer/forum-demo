package com.jornwer.forumdemo.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.ARTICLE_CREATE, Permission.COMMENT_CREATE, Permission.VOTE)),
    MUTED_USER(Set.of(Permission.VOTE)),
    MODERATOR(Set.of(Permission.ARTICLE_CREATE,
            Permission.COMMENT_CREATE,
            Permission.ARTICLE_DELETE,
            Permission.VOTE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
