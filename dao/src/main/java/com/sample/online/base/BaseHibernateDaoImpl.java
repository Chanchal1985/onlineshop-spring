package com.sample.online.base;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BaseHibernateDaoImpl<T> extends HibernateDaoSupport implements AbstractDao<T> {

    private Class< T > clazz;

    public BaseHibernateDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void create(T o) {
        getHibernateTemplate().persist(o);
    }

    @Override
    public T get(int id) {
        return getHibernateTemplate().get(clazz,id );
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().saveOrUpdate(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public List<T> find(String queryString) {
        return getHibernateTemplate().find(queryString);
    }
}