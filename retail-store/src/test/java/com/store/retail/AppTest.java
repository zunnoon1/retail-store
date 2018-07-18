package com.store.retail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.store.retail.dto.Bill;
import com.store.retail.dto.BillItem;
import com.store.retail.dto.Customer;
import com.store.retail.dto.Item;
import com.store.retail.service.BillService;
import com.store.retail.service.BillServiceImpl;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
   
	BillService s = null;
    @Before
    public void setup() {
        s = new BillServiceImpl();
    }
    
    @Test
    public void dicountForCustomer() {
    	
    	Customer c = getCustomer("Zunnoon", 1);
    	List<BillItem> items = new ArrayList<BillItem>();    	
    	items.add(getBilltItem("xyz", "10.0", "CLOTHES", "21"));    	    	    
    	items.add(getBilltItem("abc", "500.0", "CLOTHES", "22"));
    	items.add(getBilltItem("efg", "130.0", "GROCERY", "24"));
    	
    	Bill bill = getBill(items, c);
        
    	s.calculateNetPayableAmount(bill);
    
    	Assert.assertEquals("457.0", bill.getNetPayableAmount());
    	
    }
    
    @Test
    public void dicountForAffiliate() {
    	
    	Customer c = getCustomer("xyz", 11);
    	List<BillItem> items = new ArrayList<BillItem>();    	
    	items.add(getBilltItem("xyz", "10.0", "CLOTHES", "21"));    	    	    
    	items.add(getBilltItem("abc", "500.0", "CLOTHES", "22"));
    	items.add(getBilltItem("efg", "130.0", "GROCERY", "24"));
    	
    	Bill bill = getBill(items, c);
        
    	s.calculateNetPayableAmount(bill);
    	
    	Assert.assertEquals("559.0", bill.getNetPayableAmount());
    	
    }
    
    @Test
    public void dicountForOlderCustomer() {
    	
    	Customer c = getCustomer("xyz", 21);
    	List<BillItem> items = new ArrayList<BillItem>();    	
    	items.add(getBilltItem("xyz", "10.0", "CLOTHES", "21"));    	    	    
    	items.add(getBilltItem("abc", "500.0", "CLOTHES", "22"));
    	items.add(getBilltItem("efg", "130.0", "GROCERY", "24"));
    	
    	Bill bill = getBill(items, c);
        
    	s.calculateNetPayableAmount(bill);
    
    	Assert.assertEquals("584.5", bill.getNetPayableAmount());
    	
    }
    
    @Test
    public void dicountForOtherCustomer() {
    	
    	Customer c = getCustomer("xyz", 45);
    	List<BillItem> items = new ArrayList<BillItem>();    	
    	items.add(getBilltItem("xyz", "10.0", "CLOTHES", "21"));    	    	    
    	items.add(getBilltItem("abc", "500.0", "CLOTHES", "22"));
    	items.add(getBilltItem("efg", "130.0", "GROCERY", "24"));
    	
    	Bill bill = getBill(items, c);
        
    	s.calculateNetPayableAmount(bill);
   
    	Assert.assertEquals("610.0", bill.getNetPayableAmount());
    	
    }
    
    private Customer getCustomer(String name, Integer customerId) {
		Customer dto = new Customer();

		dto.setCustomerId(Integer.valueOf(customerId));

		dto.setCustomerName(name);

		return dto;
	}
    
	private  BillItem getBilltItem(String name, String price,
			String itemType, String qty) {

		BillItem dto = new BillItem();

		dto.setQty(Integer.valueOf(qty.trim()));
		dto.setItem(getItem(name, price, itemType));

		return dto;
	}

	private  Item getItem(String name, String price, String itemType) {

		Item dto = new Item();

		dto.setName(name.trim());
		dto.setItemType(itemType.trim());
		dto.setPrice(Double.valueOf(price.trim()));

		return dto;
	}
	
	private static Bill getBill(List<BillItem> billItems, Customer customer) {

		Bill dto = new Bill();
		dto.setItems(billItems);
		dto.setCustomer(customer);

		return dto;
	}
}
