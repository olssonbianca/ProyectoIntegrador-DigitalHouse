package com.finalprojectc7t3.backend.security.configuracion;

public enum UrlSecurity {
    OPEN(new String[]{
            "/api/auth/**",
            "/api/experience/{id}",
            "/api/experience/list",
            "/api/experience/category/{categoryId}",
            "/api/experience/random",
            "/api/category/list"
    }),

    ADMIN(new String[]{
            "/**"
    }),

    CLIENT(new String[]{

    });

    public final String[] urls;

    UrlSecurity(String[] urls) {
        this.urls = urls;
    }
}
