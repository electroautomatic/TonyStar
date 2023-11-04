package com.javaschool.tonystar.tonystarapi.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaschool.tonystar.tonystarapi.domain.customer.CustomerEntity;
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
    private Integer home;
    private Integer apartment;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "customerAddress")
    private List<Orders> order;
}
