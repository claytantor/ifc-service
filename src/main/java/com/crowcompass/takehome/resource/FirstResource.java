package com.crowcompass.takehome.resource;

import com.crowcompass.takehome.service.FirstService;
import com.crowcompass.takehome.service.InternationalFixedCalendarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.ws.rs.core.Response.Status;
import java.util.Map;

/**
 * Created by claytongraham on 10/25/15.
 */

@Component
@Path("/first")
public class FirstResource extends BaseResource {

    @Inject
    FirstService service;

    @Inject
    InternationalFixedCalendarService serviceIfc;

    @GET
    @Path("/model")
    @Produces("application/json")
    public Response getModel() {
        try {
            return Response.status(Status.OK).entity(buildJson(service.getModel())).build();
        } catch (JsonProcessingException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getErrorJson()).build();
        }

    }


//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/date")
//    public Response getIFCDate(@RequestBody Map<String,Object> model) {
//
//        try {
//            return Response.status(Status.OK).entity(buildJson(model)).build();
//        } catch (JsonProcessingException e) {
//            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getErrorJson()).build();
//        }
//
//    }
}