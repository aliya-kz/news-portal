package org.zhumagulova.service;


import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.models.News;

import java.util.List;


public interface NewsService {

    List<News> getAllNews();

    News getNewsById(long id);

    long createNews(News news, long newsId);

    void updateNews(News news, long id);

    @Transactional
    void delete(long id);

    void deleteSeveral(String[] ids);

    News getNewsDuplicate(long id, long langId);
}
