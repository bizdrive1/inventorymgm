package com.store.inventorymgm.exception;

public class ItemOutOfStockException extends Exception {

	static final long serialVersionUID = -486292664481433708L;
	private String itemName;
	private int quantity;
	
	public ItemOutOfStockException(String itemName,
			                       int quantity) {
	    this.itemName = itemName;
	    this.quantity = quantity;
	}
	
	@Override
	public String getMessage() {
		return "Item " + itemName + " only has " + quantity + " left.";
				
	}
}
