package com.customer.aws;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.customer.aws.entity.Customer;
import com.customer.aws.repository.CustomerRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpringbootDynamodbCustomerApplication {

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return repository.addCustomer(customer);
    }

    @GetMapping("/getCustomer/{customerId}")
    public Customer findCustomer(@PathVariable String customerId) {
        return repository.findCustomerByCustomerId(customerId);
    }
    
    @GetMapping("/getCustomers")
    public PaginatedScanList<Customer>  getCustomers() {
        return repository.listCustomers();
    }

    @DeleteMapping("/deleteCustomer")
    public String deleteCustomer(@RequestBody Customer customer) {
        return repository.deleteCustomer(customer);
    }

    @PutMapping("/editCustomer")
    public String updateCustomer(@RequestBody Customer customer) {
        return repository.editCustomer(customer);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDynamodbCustomerApplication.class, args);
    }

}
