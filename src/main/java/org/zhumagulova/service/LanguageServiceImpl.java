package org.zhumagulova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.dao.LanguageRepo;
import org.zhumagulova.models.Language;

import java.util.List;
import java.util.Locale;


@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepo languageRepo;

    @Autowired
    public LanguageServiceImpl(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }

    @Transactional
    @Override
    public long getLanguageIdByLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        return languageRepo.getIdByCode(langCode);
    }

    @Override
    public List<Language> getAll() {
        return languageRepo.getAll();
    }

    @Transactional
    @Override
    public Language getLanguageByLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        String langCode = locale.getLanguage();
        return languageRepo.getLanguageByCode(langCode);
    }
}
