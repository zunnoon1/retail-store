package com.store.retail.service;

import com.store.retail.dto.Bill;

public interface BillService {
	void calculateNetPayableAmount(Bill bill);
}
