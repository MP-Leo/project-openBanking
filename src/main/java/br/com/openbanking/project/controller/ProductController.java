package br.com.openbanking.project.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.openbanking.project.dto.ProductDto;
import br.com.openbanking.project.forms.ProductPostForm;
import br.com.openbanking.project.forms.ProductUpdateForm;
import br.com.openbanking.project.model.Product;
import br.com.openbanking.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById( @PathVariable Long id ){
		
		
		Product product = service.findById(id);
		
		return ResponseEntity.ok(new ProductDto(product));
		
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> list(){
				
		List<Product> products = service.findAll();
		
		List<ProductDto> dtos = ProductDto.convertList(products);
		
		return ResponseEntity.ok(dtos);
			
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> create(UriComponentsBuilder uriBuilder,
			@RequestBody @Valid ProductPostForm form){
		
		Product newProduct = service.save(form);
		
		URI uri = uriBuilder.path("/products/{id}")
				.buildAndExpand(newProduct.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProductDto(newProduct));
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> update(@PathVariable Long id ,
			@RequestBody ProductUpdateForm form){
		
		
		Product updatedProduct = service.update(id, form);
		
		return ResponseEntity.ok(new ProductDto(updatedProduct));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> delete(@PathVariable Long id){
		
		Product deleted = service.delete(id);
		
		return ResponseEntity.ok(new ProductDto(deleted));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDto>> search(
			@RequestParam(required = false) Double maxPricedb,
			@RequestParam(required = false) String max_price,
			@RequestParam(required = false) Double minPricedb,
			@RequestParam(required = false) String min_price,
			@RequestParam(required = false) String q){
		
		List<Product> products;
		products = service.search(maxPricedb, minPricedb, q);
		
				
		return ResponseEntity.ok(ProductDto.convertList(products));
	}
}
