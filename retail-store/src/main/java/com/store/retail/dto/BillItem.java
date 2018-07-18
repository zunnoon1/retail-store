package com.store.retail.dto;

public class BillItem {
	
	Item item;
	Integer qty;
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
