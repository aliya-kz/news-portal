package org.zhumagulova.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.exceptions.NewsAlreadyExistsException;
import org.zhumagulova.models.LocalizedNews;
import org.zhumagulova.service.NewsService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/news")
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

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


    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("news") LocalizedNews news,
                         BindingResult bindingResult, @RequestParam String newsId) throws NewsAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            return "news/error";
        }
        long id = StringUtils.hasText(newsId) ? Long.parseLong(newsId) : 0;
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
        int result = newsService.updateNews(news, id);
        return (result > 0) ? "redirect:/news" : "redirect:/news/error";
    }

    @DeleteMapping("/{id}")
    public String deleteOneNews(@PathVariable("id") long id) {
        newsService.delete(id);
        return "redirect:/news";
    }

    @DeleteMapping
    public String delete(@RequestParam String[] ids) {
        Long[] newsIds = Arrays.stream(ids).map(id->Long.parseLong(id)).toArray(Long[]::new);
        newsService.deleteSeveral(newsIds);
        return "redirect:/news";
    }


    @ExceptionHandler(value = NoSuchElementException.class)
    public String noSuchElement(Model model) {
        model.addAttribute("error_msg", "no_element");
        return "news/error";
    }

    @ExceptionHandler(NewsAlreadyExistsException.class)
    public String newsAlreadyExist (Model model) {
        model.addAttribute("error_msg", "news_exist");
        return "news/error";
    }

}
