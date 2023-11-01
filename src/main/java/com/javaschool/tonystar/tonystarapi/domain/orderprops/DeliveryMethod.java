package com.javaschool.tonystar.tonystarapi.domain.orderprops;

import com.javaschool.tonystar.tonystarapi.domain.order.Orders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String deliveryMethod;

    @OneToMany(mappedBy = "deliveryMethod")
    private List<Orders> ordersList;
}
