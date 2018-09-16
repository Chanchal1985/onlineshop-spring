package com.sample.online.dao;

import com.sample.online.base.AbstractDao;
import com.sample.online.entity.Customer;

import java.util.List;

public interface CustomerDao extends AbstractDao<Customer> {
    List<Customer> findAll();

}
