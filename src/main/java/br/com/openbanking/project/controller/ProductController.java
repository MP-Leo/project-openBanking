package br.com.openbanking.project.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.openbanking.project.dto.ProductDto;
import br.com.openbanking.project.forms.ProductPostForm;
import br.com.openbanking.project.forms.ProductUpdateForm;
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
		
		Optional<Product> optional = repository.findById(id);
		
		if(optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"Element Not Found");
		}
		
		ProductDto dto = new ProductDto(optional.get());
		
		return ResponseEntity.ok(dto);
		
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> list(){
				
		List<Product> products = repository.findAll();
		
		List<ProductDto> dtos = ProductDto.convertList(products);
		
		return ResponseEntity.ok(dtos);
			
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> create(@RequestBody ProductPostForm form,
			UriComponentsBuilder uriBuilder){
		
		Product newProduct = form.toProduct();
		
		repository.save(newProduct);
		
		URI uri = uriBuilder.path("/products/{id}")
				.buildAndExpand(newProduct.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProductDto(newProduct));
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> update(@PathVariable Long id ,
			@RequestBody ProductUpdateForm form){
		
		Optional<Product> optinal = repository.findById(id);
		
		if(optinal.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Element not Found"); 
		}
				
		Product updatedProduct = form.doUpdate(repository, id);
		
		return ResponseEntity.ok(new ProductDto(updatedProduct));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> delete(@PathVariable Long id){
		
		Optional<Product> optinal = repository.findById(id);
		
		if(optinal.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Element not Found"); 
		}
		
		Product deletedProduct = optinal.get();
		
		ProductDto dto = new ProductDto(deletedProduct);
		
		repository.delete(deletedProduct);
		
		return ResponseEntity.ok(dto);
	}
}
