package com.jornwer.forumdemo.model;

public enum Permission {
    ARTICLE_CREATE("article:create"),
    ARTICLE_DELETE("article:delete"),
    COMMENT_CREATE("comment:create"),
    VOTE("vote");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
