package com.nico.restapi2.service;

import com.nico.restapi2.exceptions.ResourceNotFoundException;
import com.nico.restapi2.model.Customer;
import com.nico.restapi2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
       Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }else{
            throw new ResourceNotFoundException("Customer", "id", customer);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer, int id) {
        Customer c = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("Customer", "id", id));
        c.setName(customer.getName());
        c.setUsername(customer.getUsername());
        c.setAddress(customer.getAddress());
        customerRepository.save(c);
        return c;
    }

    @Override
    public void deleteCustomer(int id) {
       customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.deleteById(id);
    }
}
