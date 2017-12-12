package com.store.inventorymgm.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.store.inventorymgm.exception.ItemNotFoundException;
import com.store.inventorymgm.repository.data.Item;
import com.store.inventorymgm.repository.data.Transaction;

public class TransactionRepository {

	// enable fast retrieval of the item.
	private List<Transaction> transactions;
	
	public TransactionRepository() {
		transactions = new ArrayList<>();
	}
	
	public void addTransacton(Item item,
							  int sellingQuantity,
							  Date date) throws ItemNotFoundException {
		Item transactionItem = new Item(item.getItemName(),
										item.getCostPrice(),
										item.getSellingPrice());
		transactionItem.setQuantity(sellingQuantity);
		transactions.add(new Transaction(transactionItem, date));
	}
		
	public List<Transaction> getAllTransactions(Date afterDate) {
		if (afterDate == null) {
			return transactions;
		} else {
			return transactions.stream().filter((x) -> x.getTransactionDate().after(afterDate))
					.collect(Collectors.toList());
			
		}
	}
}
