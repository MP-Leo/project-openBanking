package br.com.openbanking.project.dto;

import java.util.Objects;

import br.com.openbanking.project.model.Product;

public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	
	
	
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice().doubleValue();
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
	public String getNome() {
		return name;
	}
	public String getDescricao() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	
	
	
	
	
}
