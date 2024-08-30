package com.projeto.produtosAPI.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.produtosAPI.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
	
}
