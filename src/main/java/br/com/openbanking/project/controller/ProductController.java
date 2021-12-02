package br.com.openbanking.project.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.openbanking.project.dto.ProductDto;
import br.com.openbanking.project.model.Product;
import br.com.openbanking.project.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(
			@PathVariable Long id){
		
		Optional<Product> product = repository.findById(id);
		
		if(product == null) {
			return null;
		}
		
		ProductDto dto = new ProductDto(product.get());
		
		return ResponseEntity.ok(dto);
		
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> listProducts(){
				
		List<Product> products = repository.findAll();
		
		List<ProductDto> dtos = ProductDto.convertList(products);
		
		return ResponseEntity.ok(dtos);
			
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> createProduct(
			@RequestBody ProductForm form, UriComponentsBuilder uriBuilder){
		
		Product newProduct = form.toProduct();
		
		repository.save(newProduct);
		
		URI uri = uriBuilder.path("/products/{id}")
				.buildAndExpand(newProduct.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProductDto(newProduct));
		
	}
	

}
