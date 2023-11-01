package com.javaschool.tonystar.tonystarapi.domain.address;

import com.javaschool.tonystar.tonystarapi.domain.customer.Customer;
import com.javaschool.tonystar.tonystarapi.domain.order.Orders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String country;
    private String city;
    private Integer postalCode;
    private String street;
    private String home;
    private Integer apartment;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    @OneToMany(mappedBy = "customerAddress")
    private List<Orders> order;
}
