package com.javaschool.tonystar.tonystarapi.repos;

import com.javaschool.tonystar.tonystarapi.domain.customer.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepositry extends CrudRepository<Customer, Integer> {
}
