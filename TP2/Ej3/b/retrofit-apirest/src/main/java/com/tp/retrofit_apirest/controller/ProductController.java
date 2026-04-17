package com.tp.retrofit_apirest.controller;

import com.tp.retrofit_apirest.dto.ProductDTO;
import com.tp.retrofit_apirest.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    // Lista temporal para simular una base de datos
    private List<Product> products = new ArrayList<>(Arrays.asList(
                    new Product(1, "product1", 100),
                    new Product(2, "product2", 200),
                    new Product(3, "product3", 300),
                    new Product(4, "product4", 400)
            )
    );

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") int id) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
        int id = products.isEmpty() ? 1 : products.stream().max(Comparator.comparing(Product::getId)).get().getId() + 1;
        Product product = Product.builder()
                .id(id)
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody ProductDTO productDTO) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (product != null) {
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int id) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (product != null) {
            products.remove(product);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
