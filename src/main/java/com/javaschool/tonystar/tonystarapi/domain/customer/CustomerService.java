package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.javaschool.tonystar.tonystarapi.repos.CustomerRepositry;
import com.tsystems.javaschool.tonystar.model.CustomerInfoDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.javaschool.tonystar.tonystarapi.domain.customer.CustomerMapper.convertStringToDate;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositry customerRepositry;
    private final CustomerMapper customerMapper;

    public CustomerInfoDto createCustomer(CustomerInfoDto dto) throws ParseException {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(dto.getName());
        customerEntity.setSurname(dto.getSurname());
        customerEntity.setDayOfBirth(convertStringToDate(dto.getDayOfBirth()));
        customerEntity.setEmail(dto.getEmail());
        String id = dto.getId() != null ? dto.getId() : UUID.randomUUID().toString();
        customerEntity.setId(id);
//        if (customerRepositry.existsById(id)) {
//            throw new ResourceConflictException("Account ID already taken " + id);
//        }
        customerRepositry.save(customerEntity);
        return createCustmerDto(customerEntity);
    }

    public void updateCustomer(String id, CustomerInfoDto dto) throws ParseException {
        CustomerEntity customerEntity = loadCustomer(id);
        customerEntity.setName(dto.getName());
        customerEntity.setSurname(dto.getSurname());
        customerEntity.setDayOfBirth(convertStringToDate(dto.getDayOfBirth()));
        customerEntity.setEmail(dto.getEmail());
        customerRepositry.save(customerEntity);
    }

    public CustomerInfoDto getCustomer(String id) {
        CustomerEntity customerEntity = loadCustomer(id);
        return createCustmerDto(customerEntity);
    }

    public List<CustomerInfoDto> getCustomerList() {
        return customerRepositry.findAll().stream()
                .map(this::createCustmerDto)
                .toList();
    }

    public void deleteAccount(String id) {
        customerRepositry.deleteById(id);
    }

    public CustomerEntity loadCustomer(String id) {
        return customerRepositry.findById(id).orElseThrow();
//                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + id));
    }

    private CustomerInfoDto createCustmerDto(CustomerEntity customerEntity) {
        return customerMapper.createCustomerDto(customerEntity);
    }


}
