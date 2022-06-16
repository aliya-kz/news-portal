package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.News;

@Repository
public class NewsRepoImpl implements NewsRepo {

    private static final Logger logger = LoggerFactory.getLogger(NewsRepoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public NewsRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long createNews() {
        Session session = sessionFactory.getCurrentSession();
        News news = new News();
        long id = (long) session.save(news);
        return id;
    }

    @Override
    public boolean newsWithIdExists() {
        return false;
    }
}
