package org.zhumagulova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhumagulova.dao.NewsDao;
import org.zhumagulova.models.News;

import javax.servlet.http.HttpServletRequest;
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
    public String index(Model model, HttpServletRequest request) {
        Locale locale = request.getLocale();
        String langCode = locale.getLanguage();
        model.addAttribute("all_news", newsDao.index(langCode));
        return "news/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        Locale locale = request.getLocale();
        String langCode = locale.getLanguage();
        model.addAttribute("news", newsDao.show(id, langCode));
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
        newsDao.save(news);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        Locale locale = request.getLocale();
        String langCode = locale.getLanguage();
        model.addAttribute("news", newsDao.show(id, langCode));
        return "news/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("news") @Valid News news,
                         BindingResult bindingResult, @PathVariable("id") int id, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "news/{id}/edit";
        }
        Locale locale = request.getLocale();
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
