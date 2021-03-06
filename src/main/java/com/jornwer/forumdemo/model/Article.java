package com.jornwer.forumdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;

@Entity(name = "articles")
@Data
@NoArgsConstructor
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

    @Transient
    private String img = null;

    public Article(String text, byte[] image, String articleTitle, int rating, User user) {
        this.text = text;
        this.image = image;
        this.articleTitle = articleTitle;
        this.rating = rating;
        this.user = user;
    }

    public void addImg(){
        img = Base64.getEncoder().encodeToString(image);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                '}';
    }
}
