package org.zhumagulova.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.Language;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LanguageRepoImpl implements LanguageRepo {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long getIdByCode(String code) {
        return 0;
    }

    @Override
    public List<Language> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< Language > cq = cb.createQuery(Language.class);
        Root< Language > root = cq.from(Language.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }
}
