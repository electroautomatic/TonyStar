package com.javaschool.tonystar.tonystarapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.tonystar.tonystarapi.domain.product.Product;
import com.javaschool.tonystar.tonystarapi.repos.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//Controller without DTO
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepositoryRepository) {
        this.productRepository = productRepositoryRepository;
    }

    @ResponseBody
    @RequestMapping(path = "products/{id}")
    public String getProductPage(@PathVariable(name = "id") Integer id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);
        return json;
    }
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String title
            , @RequestParam String price) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Product n = new Product();
        n.setTitle(title);
        n.setPrice(Integer.valueOf(price));
        productRepository.save(n);
        return "Saved";
    }
}
