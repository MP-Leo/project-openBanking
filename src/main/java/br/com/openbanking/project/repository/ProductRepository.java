package br.com.openbanking.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.openbanking.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Product getByid(Long id);
	
	
	@Query(value = "SELECT * FROM Products WHERE "
			+ "(:q IS NULL OR (UPPER(Products.name)"
			+ " LIKE UPPER(CONCAT('%', :q, '%'))"
			+ "OR UPPER(Products.description) "
			+ "LIKE UPPER(CONCAT('%', :q, '%'))))"
			+ "AND (:maxPricedb IS NULL OR price <= :maxPricedb)"
			+ "AND (:minPricedb IS NULL OR price >= :minPricedb)",
			nativeQuery = true)
	List<Product> findUsingFilters(String q, Double minPricedb,
			Double maxPricedb);


}
