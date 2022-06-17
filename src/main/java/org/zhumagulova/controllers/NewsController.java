package org.zhumagulova.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.models.LocalizedNews;
import org.zhumagulova.service.NewsService;

import javax.validation.Valid;
import java.util.Arrays;
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
    public String show(@PathVariable("id") long id, Model model) {
        LocalizedNews news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news/show";
    }

    @GetMapping("/new")
    public String newNews(@ModelAttribute("news") LocalizedNews news) {
        return "news/new";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("news") LocalizedNews news,
                         BindingResult bindingResult, @RequestParam String newsId) {
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
    public String update(@Valid @ModelAttribute("news") LocalizedNews news,
                         BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "news/edit";
        }
        newsService.updateNews(news, id);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String deleteOneNews( @PathVariable("id") long id) {
        newsService.delete(id);
        return "redirect:/news";
    }

    @DeleteMapping
    public String delete( @RequestParam String [] ids) {
        logger.info ("printing ids " + ids.length);
        Arrays.stream(ids).map(Long::valueOf)
                        .forEach(id->newsService.delete(id));
        return "redirect:/news";
    }


    @ExceptionHandler(PSQLException.class)
    public String error() {
        return "news/error";
    }
}
