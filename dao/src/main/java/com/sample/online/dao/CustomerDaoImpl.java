package com.sample.online.dao;

import com.sample.online.base.BaseHibernateDaoImpl;
import com.sample.online.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl extends BaseHibernateDaoImpl<Customer> implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        return find("from customer");
    }
}
