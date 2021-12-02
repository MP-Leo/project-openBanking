package br.com.openbanking.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.openbanking.project.dto.ProductDto;
import br.com.openbanking.project.model.Product;
import br.com.openbanking.project.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> listProducts(){
				
		List<Product> products = repository.findAll();
		
		List<ProductDto> dtos = ProductDto.convertList(products);
		
		return ResponseEntity.ok(dtos);
			
	}
	
	
}
