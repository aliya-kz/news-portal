package org.zhumagulova.service;


import org.zhumagulova.models.LocalizedNews;
import java.util.List;


public interface NewsService {

    List<LocalizedNews> getAllNews();

    LocalizedNews getNewsById(long id);

    void createNews(LocalizedNews news);

    void updateNews(LocalizedNews news, long id);

    void deleteById(long id);
}
