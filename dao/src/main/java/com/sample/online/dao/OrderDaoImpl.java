package com.sample.online.dao;

import com.sample.online.base.BaseHibernateDaoImpl;
import com.sample.online.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends BaseHibernateDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }
}
