package com.javaschool.tonystar.tonystarapi.domain.order;

import com.javaschool.tonystar.tonystarapi.domain.address.Address;
import com.javaschool.tonystar.tonystarapi.domain.customer.Customer;
import com.javaschool.tonystar.tonystarapi.domain.orderprops.DeliveryMethod;
import com.javaschool.tonystar.tonystarapi.domain.orderprops.PaymentMethod;
import com.javaschool.tonystar.tonystarapi.domain.product.Product;
import com.javaschool.tonystar.tonystarapi.domain.sales.Sales;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Date orderDate;

    private String orderStatus;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address customerAddress;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "deliveryMethod_id", referencedColumnName = "id")
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order")
    private List<Sales> sale;
}
