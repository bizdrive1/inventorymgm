package com.store.inventorymgm.repository.data;

import java.util.Date;

public class Transaction {

	private Item item;
	private Date transactionDate;
	
	public Transaction(Item item,
					   Date transactionDate) {
		this.item = item;
		this.transactionDate = transactionDate;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
