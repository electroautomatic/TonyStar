package com.javaschool.tonystar.tonystarapi.domain.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.tonystar.tonystarapi.domain.customer.Customer;
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
//        Map<String, Customer> customerMap = new HashMap<>();
        for (Customer customer : mockData.customers) {
//            String photoFilename = "src/main/resources/mock-data/" + account.getId() + ".jpg";
//            byte[] photoBytes = Files.readAllBytes(Path.of(photoFilename));
//            String photoBase64 = Base64.encodeBase64String(photoBytes);
//            account.setPhoto(photoBase64);
            customerRepositry.save(customer);
//            customerMap.put(customerMap.getId(),customer);
        }
//        for (Question question : mockData.questions) {
//            question.setCreatedAt(OffsetDateTime.now());
//            question.setAuthor(accountMap.get(question.getAuthor().getId()));
//            for (Answer answer : question.getAnswers()) {
//                answer.setCreatedAt(OffsetDateTime.now());
//                answer.setQuestion(question);
//                answer.setAuthor(accountMap.get(answer.getAuthor().getId()));
//                for (Vote vote : answer.getVotes()) {
//                    vote.setAnswer(answer);
//                    vote.setUser(accountMap.get(vote.getUser().getId()));
//                }
//            }
//            questionRepository.save(question);
//        }
        for (Product products : mockData.products) {
            productRepository.save(products);
        }
    }

    private static class MockData {
        private List<Customer> customers;
        private List<Product> products;

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public List<Customer> getCustomers() {
            return customers;
        }

        public void setCustomers(List<Customer> customers) {
            this.customers = customers;
        }
    }
}
