package org.zhumagulova.service;

import org.zhumagulova.models.Language;
import java.util.List;


public interface LanguageService {
    long getLanguageIdByLocale();

    List<Language> getAll();
}
