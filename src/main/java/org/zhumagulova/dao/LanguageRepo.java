package org.zhumagulova.dao;

import org.zhumagulova.models.Language;

import java.util.List;


public interface LanguageRepo {
    long getIdByCode(String code);
    List<Language> getAll();
}

