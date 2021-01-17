package com.jornwer.forumdemo.controller;

import com.jornwer.forumdemo.model.Article;
import com.jornwer.forumdemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    private static final int maxArticlesPerPage = 10;
    private final ArticleService articleService;

    @Autowired
    public MainPageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{page}")
    public String showMainPage(@PathVariable("page") int page, Model model){
        int pages = (int) Math.ceil((double) articleService.countAllArticles() / maxArticlesPerPage);
        model.addAttribute("pages", pages);
        List<Article> articles;
        if (page < 1) {
            articles = articleService.chooseArticles(0, maxArticlesPerPage);
        } else {
            articles = articleService.chooseArticles(page-1, maxArticlesPerPage);
        }
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping
    public String showMainPage(Model model){
        int pages = (int) Math.ceil((double) articleService.countAllArticles() / maxArticlesPerPage);
        model.addAttribute("pages", pages);
        List<Article> articles = articleService.chooseArticles(0, maxArticlesPerPage);
        model.addAttribute("articles", articles);
        return "index";
    }
}
