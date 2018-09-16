package com.sample.online.dao;

import com.sample.online.base.BaseHibernateDaoImpl;
import com.sample.online.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends BaseHibernateDaoImpl<Product> implements ProductDao{
    public ProductDaoImpl() {
        super(Product.class);
    }
}
