package com.store.inventorymgm.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.store.inventorymgm.exception.ItemExistException;
import com.store.inventorymgm.exception.ItemNotFoundException;
import com.store.inventorymgm.exception.ItemOutOfStockException;
import com.store.inventorymgm.repository.data.Item;

public class InventoryRepository {

	// enable fast retrieval of the item.
	private ConcurrentHashMap<String, Item> itemMap;
	
	public InventoryRepository() {
		 itemMap = new ConcurrentHashMap<>();
	}
	
	public void createItem(String itemName,
                           float costPrice,
                           float sellingPrice) throws ItemExistException {
		Item item = itemMap.get(itemName);
		if (item == null) {
			item = new Item(itemName, costPrice, sellingPrice);
			itemMap.put(itemName, item);
		} else {
			throw new ItemExistException(itemName);
		}
	}
	
	public Item getItem(String itemName) {
		return itemMap.get(itemName);
	}
	
	public void updateBuy(String itemName,
 			  			  int quantity) throws ItemNotFoundException {
		Item item = itemMap.get(itemName);
		if (item != null) {
			item.setQuantity(item.getQuantity() + quantity);
		} else {
			throw new ItemNotFoundException(itemName);
		}
	}
	
	public void delete(String itemName) throws ItemNotFoundException {
		Item item = itemMap.remove(itemName);
		if (item == null) {
			throw new ItemNotFoundException(itemName);
		}
	}
	
	public void updateSell(String itemName, int quantity) throws ItemNotFoundException, ItemOutOfStockException {
		Item item = itemMap.get(itemName);
		if (item != null) {
			int currentQuantity = item.getQuantity();
			if (currentQuantity < quantity) {
				throw new ItemOutOfStockException(itemName, currentQuantity);
			}
			item.setQuantity(currentQuantity - quantity);
		} else {
			throw new ItemNotFoundException(itemName);
		}
	}
	
	public void updateSellPrice(String itemName, float sellingPrice) throws ItemNotFoundException {
		Item item = itemMap.get(itemName);
		if (item != null) {
			item.setSellingPrice(sellingPrice);
		} else {
			throw new ItemNotFoundException(itemName);
		}
	}
	
	public Collection<Item> getAllItems() {
		return itemMap.values();
	}
	
}
