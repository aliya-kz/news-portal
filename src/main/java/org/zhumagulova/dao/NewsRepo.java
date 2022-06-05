package org.zhumagulova.dao;

import org.zhumagulova.models.LocalizedNews;

import java.util.List;


public interface NewsRepo {
     List<LocalizedNews> getAllNews(long languageId);

    void saveNews(LocalizedNews news);

     LocalizedNews getNewsById(long id, long languageId);

     void deleteNews(long id);
}
