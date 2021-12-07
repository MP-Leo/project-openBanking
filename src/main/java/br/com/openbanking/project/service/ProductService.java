package br.com.openbanking.project.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.openbanking.project.exceptions.EntityNotFoundException;
import br.com.openbanking.project.forms.ProductPostForm;
import br.com.openbanking.project.forms.ProductUpdateForm;
import br.com.openbanking.project.model.Product;
import br.com.openbanking.project.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired 
	private ProductRepository repository;
	
	
	
	public Product findById(Long id){
		Optional<Product> optional = repository.findById(id);
		
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("Product with id: "
					+ id + " not found"); 
		}
		
		return optional.get();
	}
	
	
	public List<Product> findAll(){
		
		return repository.findAll();
		
	}

	
	public Product save(@Valid ProductPostForm form) {
		
		Product newProduct = form.toProduct();
		repository.save(newProduct);
		return newProduct;
		
	}
	
	public Product update(Long id, ProductUpdateForm form) {
		
		Product toUpdateProduct = findById(id);
		
		if(form.getDescription() != null) {
			toUpdateProduct.setDescription(form.getDescription());
		}
		
		if(form.getName() != null && !form.getName().isEmpty()) {
			toUpdateProduct.setName(form.getName());
		}
		
		if(form.getPrice() != null) {
			toUpdateProduct.setPrice(form.getPrice());
		}
		
		return toUpdateProduct;
				
	}

	public Product delete(Long id) {
		
		Product toDeleteProduct = findById(id);
		repository.delete(toDeleteProduct);
		return toDeleteProduct;
		
	}

	public List<Product> search(Double maxPricedb,
			Double minPricedb, String q) {
		
		List<Product> searched = repository.findUsingFilters(q,
				minPricedb, maxPricedb);
		return searched;
	}
	
}
