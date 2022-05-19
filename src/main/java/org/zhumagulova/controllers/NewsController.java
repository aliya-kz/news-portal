package org.zhumagulova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.dao.NewsDao;
import org.zhumagulova.models.News;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/news")

public class NewsController {

    private final NewsDao newsDao;

    @Autowired
    public NewsController(NewsDao newsDao) {
        this.newsDao =  newsDao;
    }

    @GetMapping()
    public String index(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        model.addAttribute("all_news", newsDao.getAllNews(langCode));
        return "news/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        model.addAttribute("news", newsDao.getById(id, langCode));
        return "news/show";
    }

    @GetMapping("/new")
    public String newNews(@ModelAttribute("news") News news) {
        return "news/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("news") @Valid News news,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "news/new";
        }
        newsDao.createNews(news);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,  @PathVariable("id") int id) {
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        model.addAttribute("news", newsDao.getById(id, langCode));
        return "news/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("news") @Valid News news,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "news/edit";
        }
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        newsDao.update(id, news, langCode);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)  {
        newsDao.delete(id);
        return "redirect:/news";
    }
}
