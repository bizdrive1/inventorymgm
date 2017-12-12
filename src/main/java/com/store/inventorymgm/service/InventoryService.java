package com.store.inventorymgm.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.store.inventorymgm.exception.ItemExistException;
import com.store.inventorymgm.exception.ItemNotFoundException;
import com.store.inventorymgm.exception.ItemOutOfStockException;
import com.store.inventorymgm.repository.InventoryRepository;
import com.store.inventorymgm.repository.ReportRepository;
import com.store.inventorymgm.repository.TransactionRepository;
import com.store.inventorymgm.repository.data.Item;
import com.store.inventorymgm.repository.data.Report;
import com.store.inventorymgm.repository.data.Transaction;

public class InventoryService {

	private InventoryRepository inventoryRepository;
	private TransactionRepository transactionRepository;
	private ReportRepository reportRepository;
	
	public InventoryService() {
		inventoryRepository = new InventoryRepository();
		transactionRepository = new TransactionRepository();
		reportRepository = new ReportRepository();
	}
	
	public void createItem(String itemName,
						   float costPrice,
						   float sellingPrice) throws ItemExistException {
		inventoryRepository.createItem(itemName, costPrice, sellingPrice);
	}
	
	public void updateBuy(String itemName,
			   			  int quantity) throws ItemNotFoundException {
		inventoryRepository.updateBuy(itemName, quantity);
	}
	
	public void updateSell(String itemName,
 			  int quantity,
 			  Date date) throws ItemNotFoundException, ItemOutOfStockException {
		inventoryRepository.updateSell(itemName, quantity);
		transactionRepository.addTransacton(inventoryRepository.getItem(itemName), quantity, date);
	}
	
	public void updateSellPrice(String itemName,
			  float sellPrice) throws ItemNotFoundException {
		inventoryRepository.updateSellPrice(itemName, sellPrice);
	}
	
	public void delete(String itemName,
			           Date date) throws ItemNotFoundException {
		Item item = inventoryRepository.getItem(itemName);
		inventoryRepository.delete(itemName);
		Item newItem = new Item(item.getItemName(), item.getCostPrice(), 0);
		transactionRepository.addTransacton(newItem, item.getQuantity(), date);	
	}
	
	public String generateReport() {
		Date afterDate = null;
		Report report = reportRepository.getLastReport();
		if (report != null) {
			afterDate = report.getReportDate();
		}
		Collection<Item> items = inventoryRepository.getAllItems();
		items = items.stream()
		     .sorted((s1, s2) -> s1.getItemName().compareToIgnoreCase(s2.getItemName()))
		     .collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		sb.append(getHeader());
		Float ttl = 0f;
		for(Item item : items) {
			float value = item.getCostPrice() * item.getQuantity();
			sb.append(String.format("%-12s %-12.2f %-10.2f %-15d %-13.2f\n", 
					item.getItemName(), item.getCostPrice(), item.getSellingPrice(), item.getQuantity(), value));
			ttl += value;
		}
		
		sb.append("Total value\n");
		sb.append(String.format("    %-12.2f\n", ttl));
		sb.append("Profit since previous report\n");
		List<Transaction> transactions = transactionRepository.getAllTransactions(afterDate);
		double profit = transactions.stream()
				.mapToDouble(
						x -> x.getItem().getQuantity() * (x.getItem().getSellingPrice() - x.getItem().getCostPrice()))
				.sum();
		sb.append(String.format("    %-12.2f\n", profit));
		String reportContent = sb.toString();
		reportRepository.addReport(reportContent);
		return reportContent;
	}
	
	private String getHeader() {
      	StringBuilder sb = new StringBuilder();
      	sb.append("INVENTORY REPORT\n");
      	sb.append("Item Name    Bought At    Sold At    AvailableQty    Value\n");
      	sb.append("---------    ---------    -------    ------------    -------\n");
      	return sb.toString();
	}
}
