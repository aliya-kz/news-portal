package org.zhumagulova.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhumagulova.models.News;
import org.zhumagulova.service.NewsService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class NewsControllerTest {

    @Mock
    private NewsService newsService;

    @InjectMocks
    private NewsController newsController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
    }

    @Test
    void testIndex() throws Exception {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());
        newsList.add(new News());

        when(newsService.getAllNews()).thenReturn((List) newsList);

        mockMvc.perform(get("/news"))
                .andExpect(status().isOk())
                .andExpect(view().name("/news"))
                .andExpect(model().attribute("all_news", hasSize(2)));
    }

    @Test
    void testShow() throws Exception {
        long id = 1;

        when(newsService.getNewsById(id)).thenReturn(new News());

        mockMvc.perform(get("/news/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("news/show"))
                .andExpect(model().attribute("news", instanceOf (News.class)));
    }

    @Test
    void newNews() throws Exception {
        long id = 1;

        when(newsService.getNewsById(id)).thenReturn(new News());

        mockMvc.perform(get("/news/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("news/edit"))
                .andExpect(model().attribute("news", instanceOf (News.class)));
    }

    @Test
    void create() {


    }

    @Test
    void edit() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteOneNews() {
    }

    @Test
    void delete() {
    }
}