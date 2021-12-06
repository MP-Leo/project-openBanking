package br.com.openbanking.project.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.openbanking.project.model.Product;

public class ProductPostForm {
	
	@NotNull @NotEmpty
	private String description;
	
	@NotEmpty @NotNull
	private String name;
	@NotNull
	private Double price;
	
	public Product toProduct() {
		
		return new Product(this.name, this.description, this.price);

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
