package com.jornwer.forumdemo.repository;

import com.jornwer.forumdemo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
