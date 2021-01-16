package com.jornwer.forumdemo.dto;

import com.jornwer.forumdemo.model.Article;
import com.jornwer.forumdemo.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ArticleDTO {
    @Size(min = 8, max = 128, message = "Article title size must be between 8 and 128")
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private byte[] image;

    public Article toArticle(User user){
        return new Article(text, image, title, 0, user);
    }
}
