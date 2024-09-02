package com.projeto.produtosAPI.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID id;
	
	@ManyToMany
	@JoinTable(	
			name = "order_product",
			joinColumns = @JoinColumn(
					name = "order_id",
					referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "product_id",
					referencedColumnName = "id")
			)
	@JsonManagedReference
	List<Product> products;
	
	@Column(name = "created_at")
	LocalDate createdAt;
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Order(UUID id, List<Product> products) {
		super();
		this.id = id;
		this.products = products;
	}
	
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
