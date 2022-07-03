package org.zhumagulova.service;


import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.exceptions.NewsAlreadyExistsException;
import org.zhumagulova.models.LocalizedNews;

import java.util.List;


public interface NewsService {

    List<LocalizedNews> getAllNews();

    LocalizedNews getNewsById(long id);

    long createNews(LocalizedNews news, long newsId) throws NewsAlreadyExistsException;

    int updateNews(LocalizedNews news, long id);

    @Transactional
    void delete(long id);

    @Transactional
    void deleteSeveral(Long[] ids);
}
