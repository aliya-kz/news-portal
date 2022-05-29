package org.zhumagulova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.dao.NewsRepo;
import org.zhumagulova.models.News;

import java.util.List;
import java.util.Optional;


@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private LanguageService languageService;

    @Override
    @Transactional
    public List<News> getAllNews() {
        System.out.println("newsRepo  size " + newsRepo.findAll().size());
        return newsRepo.findAll();
    }

    @Override
    @Transactional
    public News getNewsById(long id) {
        Optional<News> found = newsRepo.findById(id);
        if (found.isEmpty()) return null;
        else return found.get();
    }

    @Override
    @Transactional
    public void createNews(News news) {
        newsRepo.save(news);
    }

    @Override
    @Transactional
    public void updateNews(News news, long id) {

        long languageId = languageService.getLanguageIdByLocale();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        newsRepo.deleteById(id);
    }
}
