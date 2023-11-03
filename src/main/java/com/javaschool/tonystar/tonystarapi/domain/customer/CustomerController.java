package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.tonystar.tonystarapi.repos.CustomerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Controller with simple DTO

//@Controller
@RestController // same as Controller, but Response Bodies are by default
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepositry customerRepositry;
    private final ObjectMapper objectMapper;

//    @ResponseBody
    @RequestMapping(path = "customers/{id}")
    public CustomerInfoDto getAccount(@PathVariable(name = "id")  String id) {
        CustomerEntity customer = customerRepositry.findById(id).orElseThrow();
        CustomerInfoDto dto = createInfoDto(customer);
        return dto;
    }

//    @ResponseBody
    @RequestMapping(path = "customers", method = RequestMethod.GET)
    public List<CustomerInfoDto> getAccountList() throws JsonProcessingException {
        List<CustomerEntity> customerList = (List<CustomerEntity>) customerRepositry.findAll();
        List<CustomerInfoDto> results = new ArrayList<CustomerInfoDto>();
        for (CustomerEntity customer:customerList){
            CustomerInfoDto dto = createInfoDto(customer);
            results.add(dto);
        }
        return results;
    }

    @RequestMapping(path = "customers", method = RequestMethod.POST)
    public CustomerInfoDto createAccount(@RequestBody CustomerInfoDto dto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(dto.getName());
        customerEntity.setSurname(dto.getSurname());
        customerEntity.setDayOfBirth(dto.getDayOfBirth());
        customerEntity.setEmail(dto.getEmail());
//        customerEntity.setPassoword(dto.getPassoword());
        String id = dto.getId() != null ? dto.getId() : UUID.randomUUID().toString();
        customerEntity.setId(id);
        customerRepositry.save(customerEntity);
        return createInfoDto(customerEntity);
    }

    @RequestMapping(path = "customers/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable String id) {
        customerRepositry.deleteById(id);
    }

    @RequestMapping(path = "customers/{id}", method = RequestMethod.PUT)
    public void updateAccount(@PathVariable String id, @RequestBody CustomerInfoDto dto) {
        CustomerEntity customerEntity = customerRepositry.findById(id).orElseThrow();
        customerEntity.setName(dto.getName());
        customerEntity.setSurname(dto.getSurname());
        customerEntity.setDayOfBirth(dto.getDayOfBirth());
        customerEntity.setEmail(dto.getEmail());
        customerEntity.setPassoword(dto.getPassoword());
        customerRepositry.save(customerEntity);
    }

    private CustomerInfoDto createInfoDto(CustomerEntity customer)  {
        CustomerInfoDto dto = new CustomerInfoDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setSurname(customer.getSurname());
        dto.setDayOfBirth(customer.getDayOfBirth());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        return dto;
    }

}
