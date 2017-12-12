package com.store.inventorymgm.exception;

public class ItemNotFoundException extends Exception {

	static final long serialVersionUID = -486292664481433708L;
	private String itemName;
	
	public ItemNotFoundException(String itemName) {
	    this.itemName = itemName;	
	}
	
	@Override
	public String getMessage() {
		return "Item " + itemName + " can't be found.";
				
	}
}
