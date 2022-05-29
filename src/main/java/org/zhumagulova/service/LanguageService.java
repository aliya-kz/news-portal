package org.zhumagulova.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface LanguageService {
    long getLanguageIdByLocale();
}
