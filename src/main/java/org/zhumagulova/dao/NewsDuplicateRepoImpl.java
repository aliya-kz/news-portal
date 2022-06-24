package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.NewsDuplicate;

import java.util.List;
import java.util.Optional;

@Repository
public class NewsDuplicateRepoImpl implements NewsDuplicateRepo {

    private static final Logger logger = LoggerFactory.getLogger(NewsDuplicateRepoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public NewsDuplicateRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long createNewsDuplicate(NewsDuplicate newsDuplicate) {
        Session session = sessionFactory.getCurrentSession();
        long id = (long) session.save(newsDuplicate);
        return id;
    }

    @Override
    public boolean newsWithIdExists() {
        return false;
    }

    @Override
    public void deleteNewsDuplicate(long newsId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update NewsDuplicate set duplicatedNews.id = null where duplicatedNews.id = :newsId";
        Query query = session.createQuery(hql);
        query.setParameter("newsId", newsId);
        query.executeUpdate();
    }

    @Override
    public void deleteNewsSource(long sourceId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from NewsDuplicate where sourceNews.id = :newsId";
        Query query = session.createQuery(hql);
        query.setParameter("newsId", sourceId);
        query.executeUpdate();
    }

    @Override
    public List<NewsDuplicate> getDuplicates(long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select n from NewsDuplicate n where n.sourceNews.id = :sourceId";
        Query query = session.createQuery(hql, NewsDuplicate.class);
        query.setParameter("sourceId", id);
        List<NewsDuplicate> results = query.list();
        return results;
    }

    @Override
    public Optional <NewsDuplicate> getNewsDuplicateWhereDuplIdEqualsNull(long sourceId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select n from NewsDuplicate n where n.sourceNews.id = :sourceId and n.duplicatedNews = null";
        Query query = session.createQuery(hql, NewsDuplicate.class);
        query.setParameter("sourceId", sourceId);
        return (Optional <NewsDuplicate>) query.getSingleResult();
    }

}
