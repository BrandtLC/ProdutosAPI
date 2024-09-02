package com.projeto.produtosAPI.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.projeto.produtosAPI.model.Order;
import com.projeto.produtosAPI.model.Product;

public class OrderDTO {
	UUID id;
	List<Product> products;
	LocalDate createdAt;
	
	
	
	public OrderDTO(UUID id, List<Product> products, LocalDate createdAt) {
		super();
		this.id = id;
		this.products = products;
		this.createdAt = createdAt;
	}
	
	public OrderDTO() {
		super();
	}
	
	public OrderDTO(Order order) {
		this(order.getId(), order.getProducts(), order.getCreatedAt());
	}


	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
