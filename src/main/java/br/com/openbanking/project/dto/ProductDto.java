package br.com.openbanking.project.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.openbanking.project.model.Product;

public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	
	
	
	// CONSTRUCTORS
	public ProductDto() {
		
	}
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice().doubleValue();
	}
	
	
	
	
	public static List<ProductDto> convertList(List<Product> products) {
		List<ProductDto> dtos = new ArrayList<ProductDto>();
	
		for (Product product : products) {
			dtos.add(new ProductDto(product));
		}
		
		return dtos;
	}
		
	
	// COMPARATIVE METHOD
	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDto other = (ProductDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}
	
	
	// GETTERS
	public Long getId() {
		return id;
	}
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
