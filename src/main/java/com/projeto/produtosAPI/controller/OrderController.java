package com.projeto.produtosAPI.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.produtosAPI.dto.OrderDTO;
import com.projeto.produtosAPI.dto.ReceivedOrderDTO;
import com.projeto.produtosAPI.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value = "api/orders")
@Tag(name = "Orders Doc")
public class OrderController {
	@Autowired
	OrderService orderService;
	@Operation(summary = "Realiza busca pedidos utilizando aplicando filtro por nome do produto, data mínima e data máxima.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Busca por pedidos realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante busca por pedidos") })
	@GetMapping("/list")
	public ResponseEntity<List<OrderDTO>> retrieveAll(@RequestParam(required = false) String productName,
			@RequestParam(required = false) LocalDate maxDate,
			@RequestParam(required = false) LocalDate minDate) {
		List<OrderDTO> orders = orderService.retrieveAll(productName, maxDate, minDate);
		
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);
	}
	
	@Operation(summary = "Realiza busca de pedido por id")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Busca por pedido realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante busca por pedido") })
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> retrieveById(@PathVariable UUID id) {
		OrderDTO order = orderService.retrieveById(id);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.OK);
	}
	
	@Operation(summary = "Realiza criação de um novo pedido.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante criação de pedido") })
	@Transactional
	@PostMapping("/create")
	public ResponseEntity<OrderDTO> create(@RequestBody ReceivedOrderDTO order) {
		OrderDTO createdOrder = orderService.create(order);
		return new ResponseEntity<OrderDTO>(createdOrder, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Realiza update de itens de um pedido.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante atualização de pedido") })
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody ReceivedOrderDTO order) {
		OrderDTO updatedOrder = orderService.updateOrder(id, order);
		
		return new ResponseEntity<OrderDTO>(updatedOrder, HttpStatus.OK);
	}
	
	@Operation(summary = "Deleta pedido por id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pedido deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro ao tentar deletar pedido") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		orderService.deleteOrder(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
