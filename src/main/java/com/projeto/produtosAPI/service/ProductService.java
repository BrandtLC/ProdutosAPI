package com.projeto.produtosAPI.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projeto.produtosAPI.dto.ProductDTO;
import com.projeto.produtosAPI.exceptions.NotFoundException;
import com.projeto.produtosAPI.model.Product;
import com.projeto.produtosAPI.repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	ProductRepo productRepo;
	
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	/**
	 * Retorna todos os produtos cadastrados
	 * @return
	 */
	public List<ProductDTO> retrieveAll() {
		return productRepo.findAll()
				.stream().map(c -> new ProductDTO(c)).toList();
	}
	
	/**
	 * Retorna produto que corresponda ao id passado como parametro
	 * @param id
	 * @return
	 */
	public ProductDTO retrieveById(UUID id) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhum produto encontrado!"));
		
		return new ProductDTO(product);
	}
	
	/**
	 * Cria um novo produto com os dados contidos no objeto produtoDTO
	 * @param product
	 * @return
	 */
	@Transactional
	public ProductDTO create(ProductDTO produtoDTO) {
		Product newProduct = new Product();
		newProduct.setNome(produtoDTO.getNome());
		newProduct.setPreco(produtoDTO.getPreco());
		newProduct.setAtivo(produtoDTO.isAtivo());
		
		Product createdProduct = productRepo.save(newProduct);
		return new ProductDTO(createdProduct);
	}
	
	/**
	 * Substitui dados do produto com id igual ao id passado pelos dados contidos no objeto produtoDTO
	 * @param id
	 * @param produtoDTO
	 * @return
	 */
	@Transactional
    public ProductDTO update(UUID id, ProductDTO produtoDTO) {
		Product product = checkExistence(id);
		product.setNome(produtoDTO.getNome());
		product.setPreco(produtoDTO.getPreco());
		product.setAtivo(produtoDTO.isAtivo());

        Product updatedProduct = productRepo.save(product);

        return new ProductDTO(updatedProduct);
    }
	
	/**
	 * Deleta o produto com id igual ao id passado como parâmetro
	 * @param id
	 */
	public void delete(UUID id) {
		checkExistence(id);
		
		productRepo.deleteById(id);
	}
	
	/**
	 * Verifica a existencia de um produto no banco de dados por id, caso exista retorna o produto encontrado
	 * @param id
	 * @return
	 */
	private Product checkExistence(UUID id) {
		Product product = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado com o Id: " + id));
		
		return product;
	}
}
