package com.store.retail.dto;

import java.util.List;

public class Bill {

	private Customer customer;
	private List<BillItem> items;	
	private String actualAmount;
	private String netPayableAmount;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<BillItem> getItems() {
		return items;
	}
	public void setItems(List<BillItem> items) {
		this.items = items;
	}
	public String getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getNetPayableAmount() {
		return netPayableAmount;
	}
	public void setNetPayableAmount(String netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}
}
