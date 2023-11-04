package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.javaschool.tonystar.tonystarapi.domain.address.Address;
import com.tsystems.javaschool.tonystar.model.AddressInfoDto;
import com.tsystems.javaschool.tonystar.model.CustomerInfoDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CustomerMapper {
    CustomerInfoDto createCustomerDto(CustomerEntity customerEntity){
        CustomerInfoDto dto = new CustomerInfoDto();
        dto.setId(customerEntity.getId());
        dto.setName(customerEntity.getName());
        dto.setSurname(customerEntity.getSurname());
        dto.setDayOfBirth(convertDateToString(customerEntity.getDayOfBirth()));
        dto.setEmail(customerEntity.getEmail());
        dto.setAddress(createAddressDto(customerEntity.getAddress()));
        return dto;
    }
    private AddressInfoDto createAddressDto(Address address){
        AddressInfoDto dto = new AddressInfoDto();
        dto.setId(address.getId());
        dto.setCountry(address.getCountry());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setHome(address.getHome());
        dto.apartment(address.getApartment());
        return dto;
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateFormat.parse(dateString);
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateFormat.format(date);
    }
}

