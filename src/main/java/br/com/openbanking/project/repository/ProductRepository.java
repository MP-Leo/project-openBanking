package br.com.openbanking.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.openbanking.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
