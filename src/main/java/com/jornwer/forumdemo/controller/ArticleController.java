package com.jornwer.forumdemo.controller;

import com.jornwer.forumdemo.model.Permission;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article/")
public class ArticleController {
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('article:create')")
    public String showArticle(Model model, @PathVariable("id") int articleId){
        model.addAttribute("articleTitle", "aaaaa");
        return "article";
    }
}
