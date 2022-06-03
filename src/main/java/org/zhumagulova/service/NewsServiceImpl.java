package org.zhumagulova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.dao.NewsRepo;
import org.zhumagulova.models.LocalizedNews;


import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    @Qualifier ("newsRepoImpl")
    private NewsRepo newsRepo;

    @Autowired
    private LanguageService languageService;

    @Override
    @Transactional
    public List<LocalizedNews> getAllNews() {

        return newsRepo.getAllNews();
    }

    @Override
    @Transactional
    public LocalizedNews getNewsById(long id) {
     return null;
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
