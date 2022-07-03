package org.zhumagulova.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhumagulova.config.JPATestConfig;
import org.zhumagulova.models.LocalizedNews;
import org.zhumagulova.models.News;
import org.zhumagulova.service.NewsService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ContextConfiguration(classes = {JPATestConfig.class})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
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
    void testLoadIndexPage() throws Exception {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());
        newsList.add(new News());

        when(newsService.getAllNews()).thenReturn((List) newsList);

        mockMvc.perform(get("/news"))
                .andExpect(status().isOk())
                .andExpect(view().name("news/index"))
                .andExpect(model().attribute("all_news", hasSize(2)));
    }

    @Test
    void testLoadShowPage() throws Exception {
        long id = 1;
        LocalizedNews localizedNews = new LocalizedNews();

        when(newsService.getNewsById(id)).thenReturn(localizedNews);

        mockMvc.perform(get("/news/{1}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("news/show"))
                .andExpect(model().attribute("news", instanceOf(LocalizedNews.class)));
    }

    @Test
    void testLoadNewNewsPage() throws Exception {
        mockMvc.perform(get("/news/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("news/new"))
                .andExpect(model().attribute("news", instanceOf(LocalizedNews.class)));
    }


    @Test
    void testCreateNews() throws Exception {
        mockMvc.perform(post("/news/new")
                        .param("newsId", String.valueOf(1))
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/news"));
    }

}