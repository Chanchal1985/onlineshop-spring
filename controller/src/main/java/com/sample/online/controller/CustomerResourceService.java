package com.sample.online.controller;

import com.sample.online.dao.CustomerDao;
import com.sample.online.domain.Customer;
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
public class CustomerResourceService implements CustomerResource {

    @Autowired
    private CustomerDao customerDao;

    private EntityMapper entityMapper = EntityMapper.INSTANCE;


    public Response customer(Customer customer, UriInfo uriInfo) {
        com.sample.online.entity.Customer entity = entityMapper.pojoToEntity(customer);
        customerDao.create(entity);
        return Response.created(URI.create(uriInfo.getRequestUri().toString() + entity.getId())).build();
    }

    public Customer customer(int id) {
        final com.sample.online.entity.Customer customer = customerDao.get(id);
        if (customer == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
        return entityMapper.entityToPojo(customer);
    }

    public void customer(int id, Customer customer) {

        com.sample.online.entity.Customer customerEntity =  customerDao.get(id);
        if (customerEntity == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        customer.setId(id);
        customerDao.update(entityMapper.pojoToEntity(customer));
    }

    public List<Customer> customers() {
        List<com.sample.online.entity.Customer> customers = customerDao.findAll();
        List<Customer> customersToReturn = new ArrayList<>(customers.size());
        customers.stream().forEach( customer -> customersToReturn.add(entityMapper.entityToPojo(customer)));
        return customersToReturn;
    }
}
