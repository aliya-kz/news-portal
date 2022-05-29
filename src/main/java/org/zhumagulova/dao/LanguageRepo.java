package org.zhumagulova.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.Language;

@Repository
public interface LanguageRepo extends CrudRepository <Language, Long> {
    //TODO query
    long getIdByCode(String code);
}
