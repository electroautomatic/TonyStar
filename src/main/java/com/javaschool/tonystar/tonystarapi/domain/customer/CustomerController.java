package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.javaschool.tonystar.tonystarapi.exeption.ResourceNotFoundException;
import com.tsystems.javaschool.tonystar.api.CustomersApi;
import com.tsystems.javaschool.tonystar.model.CustomerInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerInfoDto> createCustomer(CustomerInfoDto dto) {
        CustomerInfoDto result = null;
        try {
            result = customerService.createCustomer(dto);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(201).body(result);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        customerService.deleteAccount(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<CustomerInfoDto> getCustomer(String id) {
        CustomerInfoDto result = customerService.getCustomer(id);
        System.out.println("its work");
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<CustomerInfoDto>> getCustomerList() {
        List<CustomerInfoDto> result = customerService.getCustomerList();
        return ResponseEntity.ok(result);
    }


    @Override
    public ResponseEntity<Void> updateCustomer(String id, CustomerInfoDto dto) {
        try {
            customerService.updateCustomer(id, dto);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(204).build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
