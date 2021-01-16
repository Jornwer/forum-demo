package com.jornwer.forumdemo.dto;

import com.jornwer.forumdemo.model.Article;
import com.jornwer.forumdemo.model.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;

@Data
public class ArticleDTO {
    @Size(min = 8, max = 128, message = "Article title size must be between 8 and 128")
    @NotBlank(message = "This field must contain text")
    private String title;
    @NotBlank(message = "This field must contain text")
    private String text;

    public Article toArticle(User user, MultipartFile image) throws IOException {
        return new Article(text, image == null ? null : image.getBytes(), title, 0, user);
    }
}
