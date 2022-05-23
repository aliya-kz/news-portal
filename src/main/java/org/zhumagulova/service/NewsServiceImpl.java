package org.zhumagulova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.dao.NewsDao;
import org.zhumagulova.models.News;

import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao;
    private final LanguageService languageService;

    @Autowired
    NewsServiceImpl (NewsDao newsDao, LanguageService languageService) {
        this.newsDao = newsDao;
        this.languageService = languageService;
    }

    @Override
    public List<News> getAllNews() {
        long languageId = languageService.getLanguageIdByLocale();
        return newsDao.getAllNews(languageId);
    }

    @Override
    public News getNewsById(long id) {
        long languageId = languageService.getLanguageIdByLocale();
        return newsDao.getById(id, languageId);
    }

    @Override
    public void createNews(News news) {
        newsDao.createNews(news);
    }

    @Override
    public void updateNews(News news, long id) {
        long languageId = languageService.getLanguageIdByLocale();
        newsDao.update(id, news, languageId);
    }

    @Override
    public void deleteById(long id) {
        newsDao.delete(id);
    }
}
