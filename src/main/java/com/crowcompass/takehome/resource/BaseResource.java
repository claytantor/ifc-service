package com.crowcompass.takehome.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by claytongraham on 10/25/15.
 */
public abstract class BaseResource {

    public String buildJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    public String getErrorJson(){
        return "{ \"message\": \"ERROR\" }";
    }
}