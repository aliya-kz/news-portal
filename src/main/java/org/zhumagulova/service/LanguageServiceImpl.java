package org.zhumagulova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.zhumagulova.dao.LanguageDao;

import java.util.Locale;

@Service
public class LanguageServiceImpl implements LanguageService{

    private final LanguageDao languageDao;

    @Autowired
    public LanguageServiceImpl(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public long getLanguageIdByLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        return languageDao.getIdByLanguageCode(langCode);
    }
}