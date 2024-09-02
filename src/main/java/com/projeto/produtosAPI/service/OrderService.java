package com.projeto.produtosAPI.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.projeto.produtosAPI.dto.OrderDTO;
import com.projeto.produtosAPI.dto.ReceivedOrderDTO;
import com.projeto.produtosAPI.exceptions.BadParamException;
import com.projeto.produtosAPI.exceptions.NotFoundException;
import com.projeto.produtosAPI.model.Order;
import com.projeto.produtosAPI.model.Product;
import com.projeto.produtosAPI.repository.OrderRepo;
import com.projeto.produtosAPI.repository.ProductRepo;
import com.projeto.produtosAPI.specification.OrderSpecs;

@Service
public class OrderService {
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	ProductRepo productRepo;
	
	/**
	 * Cria um novo pedido com os produtos referentes aos ids presentes em ReceivedOrderDTO
	 * @param order
	 * @return
	 */
	public OrderDTO create(ReceivedOrderDTO order) {
		//Gera lista de produtos do pedido
		List<Product> products = new ArrayList<Product>();
		for(UUID id : order.getProductIds()) {
			Optional<Product> product = productRepo.findById(id);
			if(product.isEmpty())
				throw new NotFoundException("Produto não encontrado.");
			
			validateProduct(product.get());
			products.add(product.get());
		}
		
		//Constroi pedido
		Order newOrder = new Order();
		newOrder.setProducts(products);
		
		//salva pedido
		Order createdOrder = orderRepo.save(newOrder);
		
		return new OrderDTO(createdOrder);
	}
	
	/**
	 * Recupera pedidos aplicando filtros para nome de produto, data minima e data maxima
	 * @param productName
	 * @param beforeEqual
	 * @param afterEqual
	 * @return
	 */
	public List<OrderDTO> retrieveAll(String productName, LocalDate beforeEqual, LocalDate afterEqual) {
		List<Order> orders = orderRepo.findAll(Specification
	            .where(OrderSpecs.hasProductByName(productName))
	            .and(OrderSpecs.isEqualOrEarly(beforeEqual)));
		
		return orders.stream().map(c -> new OrderDTO(c)).toList();
	}
	
	/**
	 * Recupera pedido com id passado
	 * @param id
	 * @return
	 */
	public OrderDTO retrieveById(UUID id) {
		Order order = getOrder(id);
		
		return new OrderDTO(order);
	}
	
	/**
	 * Realiza update de itens em pedido com oid passado
	 * @param id
	 * @param orderDTO
	 * @return
	 */
	public OrderDTO updateOrder(UUID id, ReceivedOrderDTO orderDTO) {
		//Recuper pedido que sera alterado
		Order order = getOrder(id);
		
		//Recupera produtos que serao atribuidos ao pedido
		List<Product> products = new ArrayList<Product>();
		for(UUID productId : orderDTO.getProductIds()) {
			Optional<Product> product = productRepo.findById(productId);
			if(product.isEmpty())
				throw new NotFoundException("Produto não encontrado.");
			
			validateProduct(product.get());
			products.add(product.get());
		}
		
		//Atribui novos produtos ao pedido
		order.setProducts(products);
		Order updatedOrder = orderRepo.save(order);
		
		return new OrderDTO(updatedOrder);
	}
	
	/**
	 * Deleta pedido com o id passado
	 * @param id
	 */
	public void deleteOrder(UUID id) {
		orderRepo.deleteById(id);
	}
	
	/**
	 * Recupera pedido e trata caso em que pedido nao e encontrado
	 * @param id
	 * @return
	 */
	private Order getOrder(UUID id) {
		Optional<Order> order = orderRepo.findById(id);
		if(order.isEmpty())
			throw new NotFoundException("Pedido não encontrado.");
		
		return order.get();
	}
	
	/**
	 * Valida produto lancando erro caso este esteja inativo
	 * @param product
	 */
	private void validateProduct(Product product) {
		if(!product.getAtivo()) 
			throw new BadParamException("Produto está inativo.");
	}
}
