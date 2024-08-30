package com.projeto.produtosAPI.dto;

public class ProductsTableItemDTO {
	private String id;
    private String nome;
    private String preco;
    private String ativo;
    
    public ProductsTableItemDTO() {
		super();
	}
    
    public ProductsTableItemDTO(ProductDTO productDTO) {
		this(productDTO.getId().toString(), productDTO.getNome(), productDTO.getPreco().toString(), 
				productDTO.isAtivo() ? "Sim" : "Não");
	}
    
	public ProductsTableItemDTO(String id, String nome, String preco, String ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.ativo = ativo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

}
