package com.store.inventorymgm.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.store.inventorymgm.exception.ItemExistException;
import com.store.inventorymgm.exception.ItemNotFoundException;
import com.store.inventorymgm.exception.ItemOutOfStockException;
import com.store.inventorymgm.repository.data.Item;

public class InventoryRepositoryTest {

	private static final String ITEM_NAME = "testItem";
	private InventoryRepository inventoryRepository;
	@Before
	public void setUp() throws Exception {
		inventoryRepository = new InventoryRepository();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testCreateItem() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		Item item = inventoryRepository.getItem(ITEM_NAME);
		assertEquals(item.getItemName(), ITEM_NAME);
		assertTrue(item.getCostPrice() == 2.0f);
		assertTrue(item.getSellingPrice() == 5.0f);
	}
	
	@Test(expected = ItemExistException.class)
    public void testCreateItemWithException() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
    }
	
	@Test
	public void testUpdateBuy() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		inventoryRepository.updateBuy(ITEM_NAME, 10);
		Item item = inventoryRepository.getItem(ITEM_NAME);
		assertTrue(item.getQuantity() == 10);
	}

	@Test(expected = ItemNotFoundException.class)
	public void testUpdateBuyWithException() throws Exception {
		inventoryRepository.updateBuy("testItem", 10);
	}
	
	@Test
	public void testUpdateSell() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		inventoryRepository.updateBuy(ITEM_NAME, 10);
		inventoryRepository.updateSell(ITEM_NAME, 6);
		Item item = inventoryRepository.getItem(ITEM_NAME);
		assertTrue(item.getQuantity() == 4);
	}
	
	@Test(expected = ItemNotFoundException.class)
	public void testUpdateSellWithItemNotFoundException() throws Exception {
		inventoryRepository.updateSell(ITEM_NAME, 6);
	}
	
	@Test(expected = ItemOutOfStockException.class)
	public void testUpdateSellWithItemOutOfStockException() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		inventoryRepository.updateBuy(ITEM_NAME, 4);
		inventoryRepository.updateSell(ITEM_NAME, 6);
	}
	
	@Test
	public void testUpdateSellPrice() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		inventoryRepository.updateSellPrice(ITEM_NAME, 6.0f);
		Item item = inventoryRepository.getItem(ITEM_NAME);
		assertTrue(item.getSellingPrice() == 6.0f);
	}
	
	@Test(expected = ItemNotFoundException.class)
	public void testUpdateSellPriceWithException() throws Exception {
		inventoryRepository.updateSellPrice(ITEM_NAME, 6.0f);
	}
	
	@Test
	public void testDelete() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		inventoryRepository.delete(ITEM_NAME);
		assertNull(inventoryRepository.getItem(ITEM_NAME));
	}
	
	@Test(expected = ItemNotFoundException.class)
	public void testDeleteWithException() throws Exception {
		inventoryRepository.delete(ITEM_NAME);
	}
	
	@Test
	public void testGetAllItems() throws Exception {
		inventoryRepository.createItem(ITEM_NAME, 2.0f, 5.0f);
		inventoryRepository.createItem(ITEM_NAME + "2", 2.0f, 5.0f);
		assertTrue(inventoryRepository.getAllItems().size() == 2);
		
		
	}
}
