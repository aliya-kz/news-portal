package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.zhumagulova.models.Language;
import org.zhumagulova.models.News;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class NewsRepoImpl implements NewsRepo {

    private static final Logger logger = LoggerFactory.getLogger(NewsRepoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public NewsRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<News> getAllNews(long languageId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select n from News n where n.language.id = :langId order by date(date) desc";
        Query query = session.createQuery(hql, News.class);
        query.setParameter("langId", languageId);
        List<News> results = query.list();
        return results;
    }

    public long createNews(News news, Language language) {
        Session session = sessionFactory.getCurrentSession();
        news.setLanguage(language);
        long id = (long) session.save(news);
        return id;
    }

    @Override
    public void updateNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedNativeQuery("updateNews");
        query.setParameter(1, news.getTitle());
        query.setParameter(2, news.getDate());
        query.setParameter(3, news.getBrief());
        query.setParameter(4, news.getContent());
        query.setParameter(5, news.getId());
        query.executeUpdate();
    }

    public News getNewsById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNamedQuery("selectNewsById", News.class);
        query.setParameter("id", id);
        Optional<News> result = query.uniqueResultOptional();
        return result.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteNews(long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from News where id = :newsId";
        Query query = session.createQuery(hql);
        query.setParameter("newsId", id);
        query.executeUpdate();
    }
}
