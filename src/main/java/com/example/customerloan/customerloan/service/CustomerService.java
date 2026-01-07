package com.example.customerloan.customerloan.service;

import java.util.List;


// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.customerloan.customerloan.constants.AppConstants;
import com.example.customerloan.customerloan.exceptions.RecordNotfoundException;
import com.example.customerloan.customerloan.model.Customer;
import com.example.customerloan.customerloan.repository.CustomerRepository;
import com.example.customerloan.customerloan.request.CustomerRequest;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer findByCustomerId(Integer id){
        return customerRepository.findById(id).orElseThrow(()-> new RecordNotfoundException("Customer id " + id + " is not found" ));
    }

    public Customer addCustomer(CustomerRequest customerRequest){
        log.info("Adding customer in db");
        log.warn("Adding customer in db");
        log.error("Adding customer in db");
        log.debug("Adding customer in db");
        log.trace("Adding customer in db");
        Customer customer = new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAddress(customerRequest.getAddress());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(CustomerRequest customerRequest){
        Customer customer = findByCustomerId(customerRequest.getId());
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAddress(customerRequest.getAddress());
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Integer id){
        Customer customer =findByCustomerId(id);
        customer.setStatus(AppConstants.INACTIVE);
        customerRepository.save(customer);
    }
}
