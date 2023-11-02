package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.tonystar.tonystarapi.repos.CustomerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//Controller with simple DTO

@Controller
//@RestController // same as Controller, but Response Bodies are by default
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepositry customerRepositry;
    @ResponseBody
    @RequestMapping(path = "customers/{id}")
    public String getAccount(@PathVariable(name = "id")  Integer id) throws Exception {
        CustomerEntity customer = customerRepositry.findById(id).orElseThrow();
        CustomerInfoDto dto = createInfoDto(customer);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);
        return json;
    }

    @ResponseBody
    @RequestMapping(path = "customers", method = RequestMethod.GET)
    public String getAccountList() throws JsonProcessingException {
        List<CustomerEntity> customerList = (List<CustomerEntity>) customerRepositry.findAll();
        List<CustomerInfoDto> results = new ArrayList<CustomerInfoDto>();
        for (CustomerEntity customer:customerList){
            CustomerInfoDto dto = createInfoDto(customer);
            results.add(dto);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(results);
        return json;
    }

    private CustomerInfoDto createInfoDto(CustomerEntity customer) {
        CustomerInfoDto dto = new CustomerInfoDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setSurname(customer.getSurname());
        dto.setDayOfBirth(customer.getDayOfBirth());
        dto.setEmail(customer.getEmail());
        return dto;
    }

}
