package com.javaschool.tonystar.tonystarapi.repos;

import com.javaschool.tonystar.tonystarapi.domain.product.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
