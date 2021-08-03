package com.example.movie_web_app_backend.services;

import com.example.movie_web_app_backend.models.Customer;
import com.example.movie_web_app_backend.models.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public void insertIntoCustomers(Customer customer) {
        repository.insert(customer);
    }

    public Optional<Customer> getACustomer(String id) throws Exception {
        Optional <Customer> customer = repository.findById(id);
        //This is saying that if customer ref variable does not contain a value then
        if(!customer.isPresent()) { //customer is not present
            throw new Exception("Customer with id " + id + " is not found");
        }
        return customer;
    }
}

