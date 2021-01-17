package com.jornwer.forumdemo.repository;

import com.jornwer.forumdemo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select count(a) from articles a")
    @Transactional
    int countAll();
}
