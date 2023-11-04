package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.javaschool.tonystar.tonystarapi.repos.CustomerRepositry;
import com.tsystems.javaschool.tonystar.model.CustomerInfoDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositry customerRepositry;
    private final CustomerMapper customerMapper;

    public CustomerInfoDto getCustomer(String id) {
        CustomerEntity customerEntity = loadCustomer(id);
        return createCustmerDto(customerEntity);
    }

    public CustomerEntity loadCustomer(String id) {
        return customerRepositry.findById(id).orElseThrow();
//                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + id));
    }

    private CustomerInfoDto createCustmerDto(CustomerEntity accountEntity) {
        return customerMapper.createCustomerDto(accountEntity);
    }
}
