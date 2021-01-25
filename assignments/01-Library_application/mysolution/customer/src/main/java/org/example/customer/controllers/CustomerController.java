package org.example.customer.controllers;

import org.example.customer.models.Customer;
import org.example.customer.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v2/customers")
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CREATE
    @RequestMapping(method = RequestMethod.PUT)
    public Customer addNewCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }


    // READ
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable String customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        }else{
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    // UPDATE
    @RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
    public Customer modifyCustomer(@RequestBody Customer customer, @RequestBody String customerId ) {
        return customerRepository.save(customer);
    }


    // DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable String customerId) {
        customerRepository.deleteById(customerId);
    }
}

