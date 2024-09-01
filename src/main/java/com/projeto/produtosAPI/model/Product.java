package com.projeto.produtosAPI.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String nome;
    private Double preco;
    private Boolean ativo;
    
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
    
    public Product() {
		super();
	}
    
	public Product(UUID id, String nome, Double preco, Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.ativo = ativo;
	}
	public UUID getId() { 	
    	return this.id;
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
