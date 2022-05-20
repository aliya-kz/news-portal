package org.zhumagulova.dao;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhumagulova.config.SpringConfig;
import org.zhumagulova.models.News;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private LanguageDao languageDao;

    @Autowired
    private NewsDao newsDao;

    @Test
    public void getAllNews() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        newsDao = context.getBean("newsDao", NewsDao.class);
        String languageCode = "en";
        List <News> allNews = newsDao.getAllNews(languageCode);
        Assertions.assertNotNull(allNews);
    }



    @Test
    public void getById() {
    }

    @Test
    public void createNews() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}