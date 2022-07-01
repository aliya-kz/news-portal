package org.zhumagulova.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;


@Controller
@RequestMapping("/news")
@ActiveProfiles("test")
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
        // string to long
        logger.info("printing ids " + ids.length);
        //transfer to long
        newsService.deleteSeveral(ids);
        return "redirect:/news";
    }


   /* @ExceptionHandler(SQLException.class)
    public String error() {
        return "news/error";
    }*/

    @ExceptionHandler(value = NewsAlreadyExistsException.class)
    public String newsAlreadyExist(Model model, NewsAlreadyExistsException exception) {
        model.addAttribute("errormessage", exception.getMessage());
        logger.info("printing exception " + exception.getClass());
        return "news/error";
    }
}
