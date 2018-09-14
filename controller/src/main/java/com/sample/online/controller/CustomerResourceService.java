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

    private static Map<Integer, Customer> customerDB = new ConcurrentHashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger();

    @Autowired
    private CustomerDao customerDao;


    public Response createCustomer(Customer customer, UriInfo uriInfo) {
        //customer.setId(idCounter.incrementAndGet());
        //customerDB.put(customer.getId(), customer);
        System.out.println("Created Customer : " + customer.getId());
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
        return customerEntity;
    }

    public Customer getCustomer(int id) {
        final Customer customer = customerDB.get(id);
        if (customer == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
        return customer;
    }

    public void updateCustomer(int id, Customer customer) {
        if (customerDB.get(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        customerDB.put(id, customer);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerDB.values());
    }
}
