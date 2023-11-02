package com.javaschool.tonystar.tonystarapi.domain.orderprops;

import com.javaschool.tonystar.tonystarapi.domain.order.Orders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String methodType;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Orders> orders;
}
