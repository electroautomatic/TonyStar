package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.tsystems.javaschool.tonystar.api.CustomersApi;
import com.tsystems.javaschool.tonystar.model.CustomerInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerInfoDto> createCustomer(CustomerInfoDto customerInfoDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerInfoDto> getCustomer(String id) {
        CustomerInfoDto result = customerService.getCustomer(id);
        System.out.println("its work");
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<CustomerInfoDto>> getCustomerList() {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateCustomer(String id, CustomerInfoDto customerInfoDto) {
        return null;
    }
}
