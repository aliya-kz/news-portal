package org.zhumagulova.dao;

import org.zhumagulova.models.Language;
import org.zhumagulova.models.LocalizedNews;

import java.util.List;


public interface LocalizedNewsRepo {

    List<LocalizedNews> getAllLocalizedNews(long languageId);

    long createLocalizedNews(LocalizedNews news, Language language);

    int updateLocalizedNews(LocalizedNews news);

    LocalizedNews getLocalizedNewsById(long id, long languageId);

    void deleteLocalizedNews(long id);
}
