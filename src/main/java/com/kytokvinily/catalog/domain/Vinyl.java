package com.kytokvinily.catalog.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Vinyl(
        @Id Long id,
        String title,
        String author,
        int year,
        @CreatedDate Instant createdDate,
        @LastModifiedDate Instant lastModifiedDate,
        @Version int version) {

    public static Vinyl of(String title, String author, int year) {
        return new Vinyl(null, title, author, year, null, null, 0);
    }
    public static Vinyl of(Long id, String title, String author, int year) {
        return new Vinyl(id, title, author, year, null, null, 0);
    }
}
