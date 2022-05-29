package org.zhumagulova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.dao.NewsRepo;
import org.zhumagulova.models.News;

import java.util.List;
import java.util.Optional;

//TODO localization
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;
    private final LanguageService languageService;


    @Autowired
    NewsServiceImpl(NewsRepo newsRepo, LanguageService languageService) {
        this.newsRepo = newsRepo;

        this.languageService = languageService;
    }


    @Override
    public List<News> getAllNews() {
        return (List<News>)newsRepo.findAll();
    }

    @Override
    public News getNewsById(long id) {
        Optional<News> found = newsRepo.findById(id);
        if (found.isEmpty()) return null;
        else return found.get();
    }

    @Override
    public void createNews(News news) {
        newsRepo.save(news);
    }

    @Override
    public void updateNews(News news, long id) {
        long languageId = languageService.getLanguageIdByLocale();
    }

    @Override
    public void deleteById(long id) {
        newsRepo.deleteById(id);
    }
}
