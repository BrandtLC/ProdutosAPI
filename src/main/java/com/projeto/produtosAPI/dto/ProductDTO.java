package com.projeto.produtosAPI.dto;

import java.util.UUID;

import com.projeto.produtosAPI.model.Product;

public class ProductDTO {
	private UUID id;
    private String nome;
    private Double preco;
    private boolean ativo;
    
    
	public ProductDTO() {
		
	}

	public ProductDTO(UUID id, String nome, Double preco, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.ativo = ativo;
	}
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.nome = product.getNome();
		this.preco = product.getPreco();
		this.ativo = product.getAtivo();
	}
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
    
    
}
