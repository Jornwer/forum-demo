package com.jornwer.forumdemo.service;

import com.jornwer.forumdemo.dto.ArticleDTO;
import com.jornwer.forumdemo.model.Article;
import com.jornwer.forumdemo.model.User;
import com.jornwer.forumdemo.repository.ArticleRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Slf4j
public class ArticleService {

    private ArticleRepository articleRepository;
    private UserService userService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
    }

    @SneakyThrows
    public void saveArticle(ArticleDTO article, String username, MultipartFile image){
        User user = userService.findUserByUsername(username).get();
        Article art = article.toArticle(user, image);
        articleRepository.save(art);
        log.info("new article with id {}", art.getId());
    }
}
