package com.sample.online.controller;

import com.sample.online.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sample.online.domain.Customer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CustomerResourceService implements CustomerResource {

    @Autowired
    private CustomerDao customerDao;


    public Response createCustomer(Customer customer, UriInfo uriInfo) {
        com.sample.online.entity.Customer entity = transform(customer);
        customerDao.create(entity);
        return Response.created(URI.create(uriInfo.getRequestUri().toString() + entity.getId())).build();
    }

    private static com.sample.online.entity.Customer transform(Customer customer) {
        com.sample.online.entity.Customer customerEntity = new com.sample.online.entity.Customer();
        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setCity(customer.getCity());
        customerEntity.setCountry(customer.getCountry());
        customerEntity.setState(customer.getState());
        customerEntity.setStreet(customer.getStreet());
        customerEntity.setId(customer.getId());
        return customerEntity;
    }

    public Customer getCustomer(int id) {
        final com.sample.online.entity.Customer customer = customerDao.get(id);
        if (customer == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
        return transform(customer);
    }

    private static Customer transform(com.sample.online.entity.Customer customerEntity) {
        com.sample.online.domain.Customer customer = new Customer();
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        customer.setCity(customerEntity.getCity());
        customer.setCountry(customerEntity.getCountry());
        customer.setState(customerEntity.getState());
        customer.setStreet(customerEntity.getStreet());
        customer.setId(customerEntity.getId());
        return customer;

    }

    public void updateCustomer(int id, Customer customer) {

        com.sample.online.entity.Customer customerEntity =  customerDao.get(id);
        if (customerEntity == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        customer.setId(id);
        customerDao.update(transform(customer));
    }

    public List<Customer> getAllCustomers() {
        List<com.sample.online.entity.Customer> customers = customerDao.findAll();
        List<Customer> customersToReturn = new ArrayList<>(customers.size());
        customers.stream().forEach( customer -> customersToReturn.add(transform(customer)));
        return customersToReturn;
    }
}
