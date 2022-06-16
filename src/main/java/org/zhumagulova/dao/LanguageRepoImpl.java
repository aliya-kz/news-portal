package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.Language;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Repository
public class LanguageRepoImpl implements LanguageRepo {

    private final SessionFactory sessionFactory;

    @Autowired
    public LanguageRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long getIdByCode(String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        List<Long> list = session.createQuery("SELECT l.id FROM Language l where l.code = :langCode" )
                .setParameter("langCode", languageCode).list();
        return list.get(0);
    }

    @Override
    public List<Language> getAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Language AS L";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public Language getLanguageByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedNativeQuery("getLanguageByCode");
        query.setParameter(1, code);
        Optional<Language> result = query.uniqueResultOptional();
        return result.orElseThrow(NoSuchElementException::new);
    }
}
