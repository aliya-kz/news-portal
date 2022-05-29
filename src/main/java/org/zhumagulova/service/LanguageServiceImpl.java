package org.zhumagulova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.zhumagulova.dao.LanguageRepo;

import java.util.Locale;

@Service
public class LanguageServiceImpl implements LanguageService{

    private final LanguageRepo languageRepo;

    @Autowired
    public LanguageServiceImpl(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }

    @Override
    public long getLanguageIdByLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        System.out.println("lang id " + languageRepo.getIdByCode(langCode));
        return languageRepo.getIdByCode(langCode);
    }
}
