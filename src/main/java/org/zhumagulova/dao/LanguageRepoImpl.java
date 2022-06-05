package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.Language;


import java.util.List;

@Repository
public class LanguageRepoImpl implements LanguageRepo {

    @Autowired
    private SessionFactory sessionFactory;

    @Override

    public long getIdByCode(String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select l.id from Language l where l.code = :language_code";
        Query query = session.createQuery(hql);
        query.setParameter("language_code", languageCode);
        List <Long> results = query.list();
        return results.get(0);
    }

    @Override
    public List<Language> getAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Language AS L";
        Query query = session.createQuery(hql);
        List results = query.list();
        return results;
    }
}
