package com.projeto.produtosAPI.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.projeto.produtosAPI.model.Order;

public interface OrderRepo  extends JpaRepository<Order, UUID>, JpaSpecificationExecutor<Order>{

}
