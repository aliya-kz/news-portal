package org.zhumagulova.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zhumagulova.dao.LocalizedNewsRepo;
import org.zhumagulova.dao.NewsRepo;
import org.zhumagulova.models.Language;
import org.zhumagulova.models.LocalizedNews;
import org.zhumagulova.models.News;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


@TestPropertySource("/application-test.properties")
@Sql(scripts = "/test-sql/create-db.sql", executionPhase = BEFORE_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
class NewsServiceImplTest {

    private final static long ID = 1L;

    @Mock
    private NewsRepo newsRepo;

    @Mock
    private LocalizedNewsRepo localizedNewsRepo;

    @Mock
    private LanguageService languageService;

    @InjectMocks
    private NewsServiceImpl newsService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(newsService).build();
    }

    @Test
    public void testGetLocalizedNewsById() {
        LocalizedNews localizedNews = new LocalizedNews.Builder("title", "brief", "content")
                .news(new News(ID)).language(new Language(ID)).date(LocalDate.now()).build();

        when(languageService.getLanguageIdByLocale()).thenReturn(ID);
        when(localizedNewsRepo.getLocalizedNewsById(ID, ID)).thenReturn(localizedNews);

        LocalizedNews actual = newsService.getNewsById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(localizedNews, actual);
    }

    @Test
    public void createNews() {
        LocalizedNews localizedNews = new LocalizedNews.Builder("title ", "brief", "content")
                .news(new News(ID)).language(new Language(ID)).date(LocalDate.now()).build();
        Language language = new Language(ID);

        when(languageService.getLanguageByLocale()).thenReturn(language);
        when(newsRepo.createNews()).thenReturn(ID);
        when(localizedNewsRepo.createLocalizedNews(localizedNews, language)).thenReturn(5L);

        long id = newsService.createNews(localizedNews, 0);
        Assertions.assertEquals(id, 5);
    }
}