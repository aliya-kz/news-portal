package org.zhumagulova.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.News;

@Repository
public class LanguageDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LanguageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long getIdByLanguageCode(String langCode) {
        return jdbcTemplate.query("SELECT id from languages where value = ?", new Object[]{langCode}, new BeanPropertyRowMapper<>(News.class))
                .stream().findAny().orElse(null).getId();
    }
}

