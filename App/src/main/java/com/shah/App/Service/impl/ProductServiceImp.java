
package com.shah.App.Service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shah.App.Service.ProductService;
import com.shah.App.model.Product;
import com.shah.App.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
