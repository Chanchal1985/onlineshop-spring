package com.sample.online.base;

import java.util.List;

public interface AbstractDao<T> {

    void create(T t);
    T get(int id);
    void update(T t);
    void delete(T t);
    List<T> findAll();

}
