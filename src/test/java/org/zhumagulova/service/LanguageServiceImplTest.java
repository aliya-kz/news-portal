package org.zhumagulova.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LanguageServiceImplTest extends BaseIntegrationTest {

    @Autowired
    private LanguageService languageService;

    @Sql(value = "classpath:test-sql/test-data.sql", executionPhase = Sql.ExecutionPhase
            .BEFORE_TEST_METHOD)
    @Test
    void getLanguageIdByLocale_LanguageExists_true() {

        assertNotNull(languageService.getLanguageByLocale());
        System.out.println(languageService.getLanguageByLocale().getCode());
    }

    @Test
    @Sql(value = "classpath:test-sql/delete-data.sql", executionPhase = Sql.ExecutionPhase
            .BEFORE_TEST_METHOD)
    void getLanguageIdByLocale_LanguageNotExist_ThrowsException() {
        assertThrows(NoSuchElementException.class, () -> languageService.getLanguageByLocale());
    }
}