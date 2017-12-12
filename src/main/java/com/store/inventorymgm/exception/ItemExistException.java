package com.store.inventorymgm.exception;

public class ItemExistException extends Exception {

	static final long serialVersionUID = -486292664481433707L;
	private String itemName;
	
	public ItemExistException(String itemName) {
	    this.itemName = itemName;	
	}
	
	@Override
	public String getMessage() {
		return "Item " + itemName + " exists.";
				
	}
}
