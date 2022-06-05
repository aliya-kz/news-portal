package org.zhumagulova.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.models.LocalizedNews;
import org.zhumagulova.service.NewsService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/news")

public class NewsController {

    @Qualifier ("newsServiceImpl")
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String index(Model model) {
        List<LocalizedNews> allNews = newsService.getAllNews();
        model.addAttribute("all_news", allNews);
        return "news/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        LocalizedNews news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news/show";
    }

    @GetMapping("/new")
    public String newNews(@ModelAttribute("news") LocalizedNews news) {
        return "news/new";
    }

    @PostMapping
    public String create(@ModelAttribute("news") @Valid LocalizedNews news,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "news/new";
        }
        newsService.createNews(news);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("news", newsService.getNewsById(id));
        return "news/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("news") @Valid LocalizedNews news,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "news/edit";
        }
        newsService.updateNews(news, id);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        newsService.deleteById(id);
        return "redirect:/news";
    }
}
