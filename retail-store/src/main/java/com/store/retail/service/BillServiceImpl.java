package com.store.retail.service;

import java.util.List;

import com.store.retail.dto.Bill;
import com.store.retail.dto.BillItem;
import com.store.retail.dto.Customer;
import com.store.retail.dto.Item;

public class BillServiceImpl implements BillService {

	private static final Double EMPLOYEE_DISCOUNT = 30d;
	private static final Double AFFILIATE_DISCOUNT = 10d;
	private static final Double OLDER_CUSTOMER_DISCOUNT = 5d;
	private static final Double HUNDRED_FIGURE_DISCOUNT = 5d;
	
	@Override
	public void calculateNetPayableAmount(Bill bill) {
		
		
		Customer customer = bill.getCustomer();
		
		if(isEmployee(customer)) {
			 getBillDiscount(bill, EMPLOYEE_DISCOUNT);
		
		} else if (isCustomerAffiliate(customer)) {
			  getBillDiscount(bill, AFFILIATE_DISCOUNT);
		
		} else if(isTwoYearsOlderCustomer(customer)) {
			 getBillDiscount(bill, OLDER_CUSTOMER_DISCOUNT);
		} else {
			 getBillDiscount(bill, 0d);
		}
	}

	private boolean isTwoYearsOlderCustomer(Customer customer) {
		
		if (customer == null) {
			return false;
		}

		if (customer.getCustomerId() > 19 && customer.getCustomerId() < 30 ) {
			return true;
		}
		return false;
	}

	private boolean isCustomerAffiliate(Customer customer) {
		if (customer == null) {
			return false;
		}

		if (customer.getCustomerId() > 9 && customer.getCustomerId() < 20 ) {
			return true;
		}
		return false;
	}
	

	private boolean isEmployee(Customer customer) {
		if(customer == null) {
			return false;
		} 
		
		if(customer.getCustomerId() > 0  && customer.getCustomerId() < 10) {
			return true;
		}
		return false;
	}

	private void getBillDiscount(Bill bill, Double percentageDiscountValue) {
		
		List<BillItem> items = bill.getItems();
		double actualAmount = 0d;
		double totalDiscount = 0d;
		
		for (BillItem item : items) {

			actualAmount += item.getItem().getPrice();
			totalDiscount += getDiscountOnItem(item.getItem(), percentageDiscountValue);

		}		
		
		// calculating on every hundred discount		
		 Double everyHundredDiscount = ((int) actualAmount/100 ) * HUNDRED_FIGURE_DISCOUNT;

				
		totalDiscount += everyHundredDiscount;
		
		bill.setActualAmount(String.valueOf(actualAmount));
		
		bill.setNetPayableAmount(String.valueOf(actualAmount - totalDiscount));		
	}

	private Double getDiscountOnItem(Item item, Double percentageDiscountValue) {
		
		if("GROCERY".equals(item.getItemType())){
			return 0d;
		}		
		return (item.getPrice() * percentageDiscountValue) / 100d;
		
	}
}
