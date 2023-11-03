package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.javaschool.tonystar.tonystarapi.domain.address.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class CustomerInfoDto {

    private Long id;

    private String name;

    private String surname;

    private Date dayOfBirth;

    private String email;

    private String passoword;

    private Address address;

}
