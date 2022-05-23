package org.zhumagulova.service;

import org.zhumagulova.models.News;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();

    News getNewsById(long id);

    void createNews(News news);

    void updateNews(News news, long id);

    void deleteById(long id);
}
