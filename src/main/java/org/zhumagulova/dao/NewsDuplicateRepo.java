package org.zhumagulova.dao;

import org.zhumagulova.models.NewsDuplicate;

import java.util.List;
import java.util.Optional;

public interface NewsDuplicateRepo {
    long createNewsDuplicate(NewsDuplicate newsDuplicate);
    boolean newsWithIdExists();
    void deleteNewsDuplicate(long newsId);

    void deleteNewsSource(long sourceId);

    List<NewsDuplicate> getDuplicates(long id);

    Optional<NewsDuplicate> getNewsDuplicateWhereDuplIdEqualsNull (long sourceId);

}
