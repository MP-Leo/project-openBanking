package br.com.openbanking.project.controller;

import br.com.openbanking.project.model.Product;

public class ProductForm {
	
	private Long id;
	private String description;
	private String name;
	private Double price;
	
	public Product toProduct() {
		
		return new Product(this.id, this.name, this.description, this.price);
		
	}
	
	public Long getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}

}
