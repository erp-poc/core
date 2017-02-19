package com.pfonseca.erp.domain;

import java.util.List;

public class Sale extends DefaultEntity {

	private Long id;
	
	private Contact client;
	private List<SaleItem> items;
	
	
	public Contact getClient() {
		return client;
	}
	public void setClient(Contact client) {
		this.client = client;
	}
	public List<SaleItem> getItems() {
		return items;
	}
	public void setItems(List<SaleItem> items) {
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
