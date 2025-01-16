package com.shah.App.Service;

import java.util.List;

import com.shah.App.model.Product;

public interface ProductService {
    
    Product createProduct(Product product);

    Product getProductById(Long productId);
    
    void deleteProduct(Long productId);
    
    Product updateProduct(Product product);
    
    List<Product> getAllProducts();
}
