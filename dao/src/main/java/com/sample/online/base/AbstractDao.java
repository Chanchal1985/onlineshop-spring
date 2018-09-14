package com.sample.online.base;

public interface AbstractDao<T> {

    void create(T t);
    T get(int id);
    void update(T t);
    void delete(T t);

}
