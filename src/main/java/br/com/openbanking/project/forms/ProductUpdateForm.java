package br.com.openbanking.project.forms;

import br.com.openbanking.project.model.Product;
import br.com.openbanking.project.repository.ProductRepository;

public class ProductUpdateForm {
	
	private String name;
	private String description;
	private Double price;
		
	
	// CONSTRUCTORS
	public ProductUpdateForm() {	
		
	}
	
	public ProductUpdateForm(String name, String description, Double price) {
		
		this.name = name;
		this.description = description;
		this.price = price;
		
	}
	
	
	public Product doUpdate(ProductRepository repository, Long id) {
				
		Product product = repository.getByid(id);
		
		product.setDescription(this.description);
		product.setName(this.name);
		product.setPrice(this.price);
		
		return product;
	}
	
	
	// GETTERS
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	
	
	
}
