package com.projeto.produtosAPI.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.projeto.produtosAPI.model.Order;

public class OrderSpecs {

	/**
	 * Cria Specification que verifica se String passada é uma substring
	 * do nome do produto.
	 * Caso nome nulo ou vazio nenhuma restricao e feita
	 * @param name
	 * @return
	 */
	public static Specification<Order> 	hasProductByName(String name) {
		Specification<Order> spec = (root, query, builder) -> {
			if (name == null || name.isEmpty()) {
                return builder.conjunction();
            }

            return builder.like(builder.lower(root.join("products").get("nome")), "%" + name.toLowerCase() + "%");
		};
		
		return spec;
	}
	
	public static Specification<Order> isEqualOrAfter(LocalDate date) {
		Specification<Order> spec = (root, query, builder) -> {
			if (date == null) {
                return builder.conjunction();
            }

            return builder.greaterThanOrEqualTo(root.get("created_at"), date);
		};
		
		return spec;
	}
	
	public static Specification<Order> isEqualOrEarly(LocalDate date) {
		Specification<Order> spec = (root, query, builder) -> {
			if (date == null) {
                return builder.conjunction();
            }

            return builder.lessThanOrEqualTo(root.get("createdAt"), date);
		};
		
		return spec;
	}
	
	public static Specification<Order> containsProduct(LocalDate date) {
		Specification<Order> spec = (root, query, builder) -> {
			if (date == null) {
                return builder.conjunction();
            }

            return builder.lessThanOrEqualTo(root.get("created_at"), date);
		};
		
		return spec;
	}
}
