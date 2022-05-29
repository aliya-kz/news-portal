package org.zhumagulova.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.Language;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {
    long getIdByCode(String code);
}
