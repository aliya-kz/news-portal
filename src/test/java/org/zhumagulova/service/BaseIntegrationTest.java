package org.zhumagulova.service;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext

public class BaseIntegrationTest {
    private static final String IMAGE_VERSION = "postgres:13.2";

    public static PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>(IMAGE_VERSION)
                .withDatabaseName("news_portal_test")
                .withUsername("postgres")
                .withPassword(System.getenv("POSTGRE_PASSWORD"))
                .withInitScript("test-sql/create-db.sql");
        postgreSQLContainer.start();
    }

   @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("db.url", postgreSQLContainer::getJdbcUrl);
    }
}
