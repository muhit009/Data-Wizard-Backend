package com.datawizard.backend.datawizardbackend.controller;

import com.datawizard.backend.datawizardbackend.domain.exception.APIBadRequestException;
import com.datawizard.backend.datawizardbackend.domain.exception.APIInternalServerErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    private final ObjectMapper objectMapper;

    @Autowired
    public BaseController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String baseURL() {
        return "{}";
    }

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode health() {
        ObjectNode output = objectMapper.createObjectNode();
        output.put("message","I am the health endpoint");
        return output;
    }

    @GetMapping(value = {"/Error"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIInternalServerErrorException error() {
        return new APIInternalServerErrorException("Example internal server error.");
    }

    @GetMapping(value = {"/400"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIBadRequestException error400() {
        return new APIBadRequestException();
    }

    @GetMapping(value = {"/500"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIInternalServerErrorException error500() {
        return new APIInternalServerErrorException();
    }
}
