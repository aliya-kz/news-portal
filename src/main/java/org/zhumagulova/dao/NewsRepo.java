package org.zhumagulova.dao;

import org.zhumagulova.models.Language;
import org.zhumagulova.models.News;

import java.util.List;


public interface NewsRepo {

    List<News> getAllNews(long languageId);

    long createNews(News news, Language language);

    void updateNews(News news);

    News getNewsById(long id);

    void deleteNews(long id);
}
