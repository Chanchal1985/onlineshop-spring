package com.sample.online.controller;

import com.sample.online.dao.ProductDao;
import com.sample.online.domain.Customer;
import com.sample.online.domain.Product;
import com.sample.online.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductResourceService implements ProductResource {

    @Autowired
    private  ProductDao productDao;

    private EntityMapper entityMapper = EntityMapper.INSTANCE;

    @Override
    public Response product(Product product, UriInfo uriInfo) {
        com.sample.online.entity.Product entity = entityMapper.pojoToEntity(product);
        productDao.create(entity);
        return Response.created(URI.create(uriInfo.getRequestUri().toString() + entity.getId())).build();
    }

    @Override
    public Product product(int id) {
        final com.sample.online.entity.Product product = productDao.get(id);
        if (product == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
        return entityMapper.entityToPojo(product);
    }

    @Override
    public void product(int id, Product product) {
        com.sample.online.entity.Product productEntity =  productDao.get(id);
        if (productEntity == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        product.setId(id);
        productDao.update(entityMapper.pojoToEntity(product));
    }

    @Override
    public List<Product> products() {
        List<com.sample.online.entity.Product> products = productDao.findAll();
        List<Product> productsResult = new ArrayList<>(products.size());
        products.stream().forEach( product -> productsResult.add(entityMapper.entityToPojo(product)));
        return productsResult;
    }
}
