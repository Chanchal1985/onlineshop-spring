package com.sample.online.base;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements AbstractDao<T> {

    private Class< T > clazz;

    public BaseDaoImpl(Class<T> clazz) {
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
        getHibernateTemplate().merge(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }
}