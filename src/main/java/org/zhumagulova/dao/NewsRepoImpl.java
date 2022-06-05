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
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class NewsRepoImpl implements NewsRepo {

    private static final Logger logger =  LoggerFactory.getLogger(NewsRepoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<LocalizedNews> getAllNews(long languageId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select ln from LocalizedNews ln where ln.language.id = :langId";
        Query query =  session.createQuery(hql);
        query.setParameter("langId", languageId);
        List <LocalizedNews> results = query.list();
        return results;
    }

    @Override
    public void saveNews(LocalizedNews news) {
    }

    @Override
    public LocalizedNews getNewsById(long id, long languageId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select ln from LocalizedNews ln where ln.news.id = :id and ln.language.id = :langId";
        Query query =  session.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("langId", languageId);
       Optional<LocalizedNews> result = query.uniqueResultOptional();
        return result.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteNews(long id) {

    }

}
