package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.Language;
import org.zhumagulova.models.LocalizedNews;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class LocalizedNewsRepoImpl implements LocalizedNewsRepo {

    private static final Logger logger = LoggerFactory.getLogger(LocalizedNewsRepoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public LocalizedNewsRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<LocalizedNews> getAllLocalizedNews(long languageId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select ln from LocalizedNews ln where ln.language.id = :langId";
        Query query = session.createQuery(hql, LocalizedNews.class);
        query.setParameter("langId", languageId);
        List<LocalizedNews> results = query.list();
        return results;
    }

    public long createLocalizedNews(LocalizedNews news, Language language) {
        Session session = sessionFactory.getCurrentSession();
        news.setLanguage(language);
        return (long) session.save(news);
    }

    @Override
    public void updateLocalizedNews(LocalizedNews news) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedNativeQuery("updateLocalizedNews");
        query.setParameter(1, news.getTitle());
        query.setParameter(2, news.getDate());
        query.setParameter(3, news.getBrief());
        query.setParameter(4, news.getContent());
        query.setParameter(5, news.getNews().getId());
        query.setParameter(6, news.getLanguage().getId());
        query.executeUpdate();
    }

    @Override
    public LocalizedNews getLocalizedNewsById(long id, long languageId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNamedQuery("selectLocalizedNewsById", LocalizedNews.class);
        query.setParameter("id", id);
        query.setParameter("langId", languageId);
        Optional<LocalizedNews> result = query.uniqueResultOptional();
        return result.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteLocalizedNews(long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<LocalizedNews> criteriaDelete = cb.createCriteriaDelete(LocalizedNews.class);
        Root<LocalizedNews> root = criteriaDelete.from(LocalizedNews.class);
        criteriaDelete.where(cb.equal(root.get("id"), id));
        session.createQuery(criteriaDelete).executeUpdate();
    }
}
