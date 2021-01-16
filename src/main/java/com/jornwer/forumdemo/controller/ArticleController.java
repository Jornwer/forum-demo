package com.jornwer.forumdemo.controller;

import com.jornwer.forumdemo.dto.ArticleDTO;
import com.jornwer.forumdemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/article/")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("{id}")
    public String showArticle(Model model, @PathVariable("id") int articleId){
        model.addAttribute("articleTitle", "aaaaa");
        return "article";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('article:create')")
    public String addArticle(Model model){
        model.addAttribute("ArticleDTO", new ArticleDTO());
        return "create_article";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('article:create')")
    public String postArticle(@RequestParam("image") MultipartFile image,
                              @Validated @ModelAttribute("ArticleDTO") ArticleDTO article,
                              Errors errors,
                              HttpServletRequest request){
        if (errors.hasErrors()){
            return "create_article";
        }
        articleService.saveArticle(article, request.getRemoteUser(), image);
        return "redirect:/";
    }
}
