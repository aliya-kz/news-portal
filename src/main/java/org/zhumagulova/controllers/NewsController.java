package org.zhumagulova.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.exceptions.NewsAlreadyExistException;
import org.zhumagulova.models.News;
import org.zhumagulova.service.LanguageService;
import org.zhumagulova.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/news")

public class  NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private final NewsService newsService;

    private final LanguageService languageService;

    @Autowired
    public NewsController(NewsService newsService, LanguageService languageService) {
        this.newsService = newsService;
        this.languageService = languageService;
    }

    @GetMapping
    public String index(Model model) {
        List<News> allNews = newsService.getAllNews();
        model.addAttribute("all_news", allNews);
        return "news/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model, HttpServletRequest request) {
        String locale = request.getParameter("locale");
        News news = newsService.getNewsById(id);
        if (locale != null) {
            long langId = languageService.getLanguageIdByLocale();
            news = newsService.getNewsDuplicate(id, langId);
        }
        model.addAttribute("news", news);
        return "news/show";
    }

    @GetMapping("/new")
    public String newNews(@ModelAttribute("news") News news) {
        return "news/new";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("news") News news,
                         BindingResult bindingResult, @RequestParam String newsId) throws NewsAlreadyExistException {
        if (bindingResult.hasErrors()) {
            return "news/error";
        }
        long id = (newsId.isBlank()) ? 0 : Long.parseLong(newsId);
        newsService.createNews(news, id);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("news", newsService.getNewsById(id));
        return "news/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("news") News news,
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
        newsService.deleteSeveral(ids);
        return "redirect:/news";
    }

/*
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String error(Model model) {
        model.addAttribute("exception", "news_exist");
        return "news/error";
    }*/
}
