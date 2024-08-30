package com.projeto.produtosAPI.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.produtosAPI.dto.ProductDTO;
import com.projeto.produtosAPI.model.Product;
import com.projeto.produtosAPI.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
	
	ProductService service;
	
	public ProductController(ProductService service) {
		this.service = service;
	}
    
	@GetMapping("/")
    public ResponseEntity<List<ProductDTO>> retrieveAll() {
		List<ProductDTO> products = service.retrieveAll();
		
		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> retrieveById(@PathVariable UUID id) {
        ProductDTO product = service.retrieveById(id);
        
        return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
        ProductDTO createdProduct = service.create(product);

    	return new ResponseEntity<ProductDTO>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductDTO product) {
    	ProductDTO updatedProduct = service.update(id, product);
		return new ResponseEntity<ProductDTO>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
