package com.example.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.repo.ProductRepository;

@RequestMapping("/product-manager")
@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/save")
	public String saveAllProducts() {

		List<Product> list = new ArrayList<>();
		list.add(new Product("PEN", 12.213, "RED"));
		list.add(new Product("PENCIL", 124.213, "BLACK"));
		list.add(new Product("MOUSE", 142.213, "RED"));
		productRepository.saveAll(list);
		return "saved successfully";
	}

	@RequestMapping("/get-by-id/{id}")
	@Cacheable("id")
	public Optional<Product> getProduct(@PathVariable Integer id) {
		return productRepository.findById(id);

	}
}
