package com.nico.restapi2.service;

import com.nico.restapi2.model.Customer;
import java.util.List;

public interface CustomerServiceInterface {
    Customer saveCustomer(Customer customer);
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    Customer updateCustomer(Customer customer, int id);
    void deleteCustomer(int id);
}
