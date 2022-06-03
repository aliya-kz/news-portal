package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.LocalizedNews;

import java.util.List;

@Repository
public class NewsRepoImpl implements NewsRepo {

    private static final Logger logger =  LoggerFactory.getLogger(NewsRepoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<LocalizedNews> getAllNews() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM LocalizedNews";
        Query query = session.createQuery(hql);
        List results = query.list();
        return results;
    }

    @Override
    public void saveNews(LocalizedNews news) {

    }

    @Override
    public LocalizedNews getNews(long id) {
        return null;
    }

    @Override
    public void deleteNews(long id) {

    }

}
