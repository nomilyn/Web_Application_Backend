package com.example.movie_web_app_backend.controllers;

import com.example.movie_web_app_backend.CustomizedResponse;
import com.example.movie_web_app_backend.models.Customer;
import com.example.movie_web_app_backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService service;

    //to be deleted soon. This is for just testing purposes in PostMan
    @GetMapping("/customers")
    public ResponseEntity getcustomers() {
        var customizedResponse = new CustomizedResponse("A list of customers", service.getCustomers());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //customer registration
    @PostMapping(value = "/customers", consumes = { //consume -> sending data to the body of the request
            MediaType.APPLICATION_JSON_VALUE
    })

    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        service.insertIntoCustomers(customer);
        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getACustomer(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Customer with id " +id, Collections.singletonList((service.getACustomer(id))));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
