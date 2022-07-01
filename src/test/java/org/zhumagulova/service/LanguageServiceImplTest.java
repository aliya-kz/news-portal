package org.zhumagulova.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.zhumagulova.config.JPATestConfig;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = JPATestConfig.class)
@ActiveProfiles("test")


class LanguageServiceImplTest {

    @Autowired
    private LanguageService languageService;


    @Sql(value = "classpath:test-sql/test-data.sql", executionPhase = Sql.ExecutionPhase
            .BEFORE_TEST_METHOD)
    @Test
    void getLanguageIdByLocale_LanguageExists_true() {
        assertNotNull(languageService.getLanguageByLocale());
    }

    @Test
    @Sql(value = "classpath:test-sql/delete-data.sql", executionPhase = Sql.ExecutionPhase
            .BEFORE_TEST_METHOD)
    void getLanguageIdByLocale_LanguageNotExist_ThrowsException() {
        assertThrows(NoSuchElementException.class, () -> languageService.getLanguageByLocale());
    }
}