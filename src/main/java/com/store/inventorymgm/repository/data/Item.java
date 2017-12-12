package com.store.inventorymgm.repository.data;

public class Item {
	private String itemName;
	private float costPrice;
	private float sellingPrice;
	private int quantity;
	
	/**
	 * @param itemName
	 * @param costPrice
	 * @param sellingPrice
	 */
	public Item(String itemName,
				float costPrice,
				float sellingPrice) {
		this.itemName = itemName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
	}

	/**
	 * @return
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return
	 */
	public float getCostPrice() {
		return costPrice;
	}

	/**
	 * @param costPrice
	 */
	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * @return
	 */
	public float getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * @param sellingPrice
	 */
	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
