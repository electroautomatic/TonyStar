package com.javaschool.tonystar.tonystarapi.domain.product;

import com.javaschool.tonystar.tonystarapi.domain.order.Orders;
import com.javaschool.tonystar.tonystarapi.domain.sales.Sales;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String title;

    private Integer price;

    private String category;

    private String brand;

    private String color;

    private String weight;

    private String volume;

    private Integer quantityInStock;

    @OneToMany(mappedBy = "product")
    private List<Sales> sale;
}
