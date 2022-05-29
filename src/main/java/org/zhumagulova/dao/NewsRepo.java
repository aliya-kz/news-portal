package org.zhumagulova.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zhumagulova.models.News;

@Repository
public interface NewsRepo extends CrudRepository <News, Long> {
}
