package org.zhumagulova.dao;

public interface NewsRepo {
    long createNews();
    boolean newsWithIdExists();
}
