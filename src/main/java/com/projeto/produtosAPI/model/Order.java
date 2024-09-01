package com.projeto.produtosAPI.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	List<Product> products;
}
