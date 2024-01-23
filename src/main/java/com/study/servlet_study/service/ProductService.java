package com.study.servlet_study.service;

import com.study.servlet_study.entity.Product;
import com.study.servlet_study.repository.ProductRepository;

public class ProductService {
	
	private ProductRepository productRepository;
	private static ProductService instance;
	
	private ProductService() {
		productRepository = ProductRepository.getInstance();
		
	}
	
	public static ProductService getInstanct() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public int addProduct(Product product) {
		if(productRepository.isDuplicationProduct(product.getProductName())) {
			return 0;
		}
		return productRepository.saveProduct(product);
	}
	
	public Product getProduct(String productName) {
		return productRepository.findProductByProductName(productName);
	}
}
