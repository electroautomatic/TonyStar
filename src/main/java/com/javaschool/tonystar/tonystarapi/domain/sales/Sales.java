package com.javaschool.tonystar.tonystarapi.domain.sales;

import com.javaschool.tonystar.tonystarapi.domain.order.Orders;
import com.javaschool.tonystar.tonystarapi.domain.orderprops.DeliveryMethod;
import com.javaschool.tonystar.tonystarapi.domain.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sales {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
