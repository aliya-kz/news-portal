package org.zhumagulova.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.models.News;


import java.time.LocalDate;
import java.util.List;


@Repository("newsDao")
public class NewsDao {

    private final JdbcTemplate jdbcTemplate;
    private final LanguageDao languageDao;

    @Autowired
    public NewsDao(JdbcTemplate jdbcTemplate, LanguageDao languageDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.languageDao = languageDao;
    }

    public List<News> getAllNews(long languageId) {
        return jdbcTemplate.query("SELECT * from news_lang where language = ?", new Object[]{languageId}, new BeanPropertyRowMapper<>(News.class));
    }

    public News getById(long id, long languageId) {
        return jdbcTemplate.query("SELECT n.date, nl.* from news n left join news_lang nl on n.id=nl.id where n.id = ? and language = ?", new Object[]{id, languageId}, new BeanPropertyRowMapper<>(News.class))
                .stream().findAny().orElse(null);
    }

    @Transactional
    public void createNews(News news) {
        LocalDate currentDate = LocalDate.now();

        jdbcTemplate.update("INSERT into news values (?)",currentDate);
        long id = jdbcTemplate.query("SELECT max(id) from news", new BeanPropertyRowMapper<>(News.class))
                .stream().findAny().orElse(null).getId();
        jdbcTemplate.update("INSERT into news_lang values (?, ?, ?, ?) where id = ?", news.getTitle(), news.getBrief(), news.getContent(),
                news.getLanguage(), id);
    }

    public void update(long id, News updatedNews, long languageId) {
        jdbcTemplate.update("UPDATE news_lang set title=?, brief=?, content=? where id=? and language=?",
                updatedNews.getTitle(), updatedNews.getBrief(), updatedNews.getContent(), id,
                languageId);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE from news where id = ?", id);
    }
}