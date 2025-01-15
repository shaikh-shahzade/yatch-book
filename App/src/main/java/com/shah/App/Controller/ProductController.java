package com.shah.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shah.App.Service.ProductService;
import com.shah.App.model.Product;

@RestController
@RequestMapping("api")
public class ProductController {
	
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
    
}
