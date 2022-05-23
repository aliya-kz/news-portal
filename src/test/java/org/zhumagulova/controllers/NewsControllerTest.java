package org.zhumagulova.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhumagulova.dao.NewsDao;
import org.zhumagulova.models.News;
import org.zhumagulova.service.NewsService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NewsControllerTest {

    @InjectMocks
    private NewsController newsController;

    @Mock
    private NewsService newsService;

    private MockMvc mockMvc;

    private long languageId = 1;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
    }

    @Test
    public void index() throws Exception {
        News first = new News();
        News second = new News();
        List<News> list = Arrays.asList(first, second);
        when(newsService.getAllNews()).thenReturn((List)list);

        mockMvc.perform(get("/news"))
                .andExpect(status().isOk())
                .andExpect(view().name("news/index"))
                .andExpect(model().attribute("all_news", hasItems(first)));
    }

    @Test
    public void show() throws Exception {
        long id = 5;
        News testNews = new News();
        testNews.setId(id);
        when(newsService.getNewsById(id)).thenReturn(testNews);

        mockMvc.perform(get("/news/5"))
                .andExpect(status().isOk())
                .andExpect(view().name("news/show"))
                .andExpect(model().attribute("news", instanceOf(News.class)));
    }

    @Test
    public void newNews() throws Exception {
        mockMvc.perform(get("/news/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("news/new"))
                .andExpect(model().attribute("news", instanceOf(News.class)));
    }

    @Test
    public void create() {

    }

    @Test
    public void edit() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}