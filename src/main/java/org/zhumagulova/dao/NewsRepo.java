package org.zhumagulova.dao;

import org.springframework.stereotype.Repository;
import org.zhumagulova.models.LocalizedNews;

import java.util.List;


public interface NewsRepo {
     List<LocalizedNews> getAllNews();

    void saveNews(LocalizedNews news);

     LocalizedNews getNews(long id);

     void deleteNews(long id);
}
