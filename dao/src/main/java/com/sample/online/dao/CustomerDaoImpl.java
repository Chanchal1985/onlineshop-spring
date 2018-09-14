package com.sample.online.dao;

import com.sample.online.base.BaseDaoImpl;
import com.sample.online.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }
}
