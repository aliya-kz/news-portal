package org.zhumagulova.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zhumagulova.models.News;

import java.util.List;

@Service
public interface NewsService {

    List<News> getAllNews();

    News getNewsById(long id);

    void createNews(News news);

    void updateNews(News news, long id);

    void deleteById(long id);
}
