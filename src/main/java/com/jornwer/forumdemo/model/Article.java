package com.jornwer.forumdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", length = 4096)
    private String text;

    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name = "article_title", length = 128, unique = true)
    private String articleTitle;

    @Column(name = "rating")
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User user;
}
