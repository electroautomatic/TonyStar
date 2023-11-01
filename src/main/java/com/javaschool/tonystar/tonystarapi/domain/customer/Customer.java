package com.javaschool.tonystar.tonystarapi.domain.customer;

import com.javaschool.tonystar.tonystarapi.domain.address.Address;
import com.javaschool.tonystar.tonystarapi.domain.order.Orders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private Date dayOfBirth;

    private String email;

    private String passoword;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> ordersList;

}
