package org.zhumagulova.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.models.LocalizedNews;
import org.zhumagulova.service.NewsService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping("/news")

public class NewsController {

    private static final Logger logger = LogManager.getLogger(NewsController.class);

    private final NewsService newsService;

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
                         BindingResult bindingResult, @RequestParam String newsId) {
        logger.info("printing newsId in controller:" + newsId);
        if (bindingResult.hasErrors()) {
            return "news/error";
        }
        long id = (newsId.length() < 1) ? 0 : Long.parseLong(newsId);
        newsService.createNews(news, id);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("news", newsService.getNewsById(id));
        return "news/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("news") @Valid LocalizedNews news,
                         BindingResult bindingResult, @PathVariable("id") long id) {
        logger.info("printing patch id :" + id);
        logger.info("printing patch news title :" + news.getTitle());
        if (bindingResult.hasErrors()) {
            return "news/edit";
        }
        newsService.updateNews(news, id);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String delete( @PathVariable("id") long id) {
        newsService.delete(id);
        return "redirect:/news";
    }

    @ExceptionHandler(Exception.class)
    public String error() {
        return "news/error";
    }
}
