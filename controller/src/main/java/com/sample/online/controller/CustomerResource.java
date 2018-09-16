package com.sample.online.controller;

import com.sample.online.domain.Customer;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/jaxb/customers")
public interface CustomerResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response customer(Customer customer, @Context UriInfo uriInfo);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Customer customer(@PathParam("id") int id);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void customer(@PathParam("id") int id, Customer customer);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> customers();
}
