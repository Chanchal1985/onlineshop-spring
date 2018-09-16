package com.sample.online.controller;

import com.sample.online.domain.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/product")
public interface ProductResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response product(Product product, @Context UriInfo uriInfo);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Product product(@PathParam("id") int id);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void product(@PathParam("id") int id, Product product);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> products();
}
