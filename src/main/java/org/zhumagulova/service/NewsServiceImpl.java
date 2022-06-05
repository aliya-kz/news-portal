package org.zhumagulova.service;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.dao.NewsRepo;
import org.zhumagulova.models.LocalizedNews;


import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger("NewsServiceImpl");

    @Autowired
    @Qualifier ("newsRepoImpl")
    private NewsRepo newsRepo;

    @Autowired
    private LanguageService languageService;


    @Override
    @Transactional
    public List<LocalizedNews> getAllNews() {
        long languageId = languageService.getLanguageIdByLocale();
        return newsRepo.getAllNews(languageId);
    }

    @Override
    @Transactional
    public LocalizedNews getNewsById(long id) {
        long languageId = languageService.getLanguageIdByLocale();
        return newsRepo.getNewsById(id, languageId);
    }

    @Override
    public void createNews(LocalizedNews news) {

    }


    @Override
    public void updateNews(LocalizedNews news, long id) {
        long languageId = languageService.getLanguageIdByLocale();
    }

    @Override
    @Transactional
    public void deleteById(long id) {

    }

}
