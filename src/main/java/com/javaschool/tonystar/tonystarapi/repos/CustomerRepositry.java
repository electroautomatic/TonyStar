package com.javaschool.tonystar.tonystarapi.repos;

import com.javaschool.tonystar.tonystarapi.domain.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositry extends JpaRepository<CustomerEntity, String> {
}
