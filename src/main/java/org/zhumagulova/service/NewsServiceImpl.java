package org.zhumagulova.service;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.dao.LocalizedNewsRepo;
import org.zhumagulova.dao.NewsRepo;
import org.zhumagulova.models.Language;
import org.zhumagulova.models.LocalizedNews;
import org.zhumagulova.models.News;


import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger("NewsServiceImpl");

    private final LocalizedNewsRepo localizedNewsRepo;

    private final NewsRepo newsRepo;

    private final LanguageService languageService;

    @Autowired
    public NewsServiceImpl(LocalizedNewsRepo localizedNewsRepo, NewsRepo newsRepo, LanguageService languageService) {
        this.localizedNewsRepo = localizedNewsRepo;
        this.newsRepo = newsRepo;
        this.languageService = languageService;
    }

    @Override
    @Transactional
    public List<LocalizedNews> getAllNews() {
        long languageId = languageService.getLanguageIdByLocale();
        return localizedNewsRepo.getAllLocalizedNews(languageId);
    }

    @Override
    @Transactional
    public LocalizedNews getNewsById(long id) {
        long languageId = languageService.getLanguageIdByLocale();
        return localizedNewsRepo.getLocalizedNewsById(id, languageId);
    }

    @Override
    @Transactional
    public long createNews(LocalizedNews localizedNews, long newsId) {
        News news = new News();
        newsId = (newsId == 0) ? newsRepo.createNews() : newsId;
        news.setId(newsId);
        localizedNews.setNews(news);
        Language language = languageService.getLanguageByLocale();
        return localizedNewsRepo.createLocalizedNews(localizedNews, language);
    }


    @Override
    @Transactional
    public void updateNews(LocalizedNews localizedNews, long id) {
        Language language = languageService.getLanguageByLocale();
        localizedNews.setLanguage(language);
        News news = new News();
        news.setId(id);
        localizedNews.setNews(news);
        localizedNewsRepo.updateLocalizedNews(localizedNews);
    }

    @Override
    @Transactional
    public void delete(long id) {
        localizedNewsRepo.deleteLocalizedNews(id);
    }

}
