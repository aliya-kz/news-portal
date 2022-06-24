package org.zhumagulova.service;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.zhumagulova.config.WebMvcConfig;
import org.zhumagulova.dao.LanguageRepo;
import org.zhumagulova.dao.LanguageRepoImpl;


class LanguageServiceImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private LanguageService languageService;

     private LanguageRepo languageRepo;

    public LanguageServiceImplTest(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @BeforeEach
    public void setUp () {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebMvcConfig.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
        languageRepo = new LanguageRepoImpl(sessionFactory);
        languageService = new LanguageServiceImpl(languageRepo);
    }

    @Test
    void getLanguageIdByLocale() {
        long id = languageService.getLanguageIdByLocale();
        Assertions.assertEquals(id, 1);
    }

    @Test
    void getLanguageByLocale() {
    }
}