package com.javaschool.tonystar.tonystarapi.domain.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.tonystar.tonystarapi.domain.customer.CustomerEntity;
import com.javaschool.tonystar.tonystarapi.domain.product.Product;
import com.javaschool.tonystar.tonystarapi.repos.CustomerRepositry;
import com.javaschool.tonystar.tonystarapi.repos.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class MockDataInitializer {
    private final CustomerRepositry customerRepositry;
    private final ProductRepository productRepository;

    @Value("${app.staging:prod}")
    private String staging;



    public MockDataInitializer(CustomerRepositry customerRepositry, ProductRepository productRepository) {
        this.customerRepositry = customerRepositry;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void run() throws Exception {
        if (staging.equalsIgnoreCase("prod")) {
            System.out.println("Data initializer is not allowed at PROD");
            return;
        }
        System.out.println("Data initializer runs");
        if (customerRepositry.count() > 0) {
            System.out.println("DB is not empty, skipping data init step");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String dataJson = Files.readString(Path.of("src/main/resources/mock-data/data.json"));
        MockData mockData = objectMapper.readValue(dataJson, MockData.class);
        for (CustomerEntity customer : mockData.customers) {
            customer.setAddress(customer.getAddress());
            customerRepositry.save(customer);
        }
        for (Product products : mockData.products) {
            productRepository.save(products);
        }
    }
    private static class MockData {
        private List<CustomerEntity> customers;
        private List<Product> products;

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public List<CustomerEntity> getCustomers() {
            return customers;
        }

        public void setCustomers(List<CustomerEntity> customers) {
            this.customers = customers;
        }
    }
}
