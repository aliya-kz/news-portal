package org.zhumagulova.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.News;

@Repository
public interface NewsRepo extends JpaRepository<News, Long> {
}
