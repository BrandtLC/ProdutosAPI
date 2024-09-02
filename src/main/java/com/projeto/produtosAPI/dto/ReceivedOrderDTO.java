package com.projeto.produtosAPI.dto;

import java.util.List;
import java.util.UUID;

public class ReceivedOrderDTO {
	List<UUID> productIds;

	public List<UUID> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<UUID> productIds) {
		this.productIds = productIds;
	}
	
}
