package com.jornwer.forumdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ArticleController {
    @GetMapping("{id}")
    public String showArticle(Model model, @PathVariable("id") int articleId){
        model.addAttribute("articleTitle", "aaaaa");
        return "article";
    }
}
