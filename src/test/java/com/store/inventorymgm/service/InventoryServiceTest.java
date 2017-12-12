package com.store.inventorymgm.service;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryServiceTest {

	private InventoryService inventoryService;
	
	@Before
	public void setUp() throws Exception {
		inventoryService = new InventoryService();
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInventoryService() throws Exception {
		
		inventoryService.createItem("Book01", 10.50f, 13.79f);
		inventoryService.createItem("Food01", 1.47f, 3.98f);
		inventoryService.createItem("Med01", 30.63f, 34.29f);
		inventoryService.createItem("Tab01", 57.00f, 84.98f);
		inventoryService.updateBuy("Tab01", 100);
		inventoryService.updateSell("Tab01", 2, new Date());
		inventoryService.updateBuy("Food01", 500);
		inventoryService.updateBuy("Book01", 100);
		inventoryService.updateBuy("Med01", 100);
		inventoryService.updateSell("Food01", 1, new Date());
		inventoryService.updateSell("Food01", 1, new Date());
		inventoryService.updateSell("Tab01", 2, new Date());
		inventoryService.generateReport();
		inventoryService.delete("Book01", new Date());
		inventoryService.updateSell("Tab01", 5, new Date());
		inventoryService.createItem("Mobile01", 10.51f, 44.56f);
		inventoryService.updateBuy("Mobile01", 250);
		inventoryService.updateSell("Food01", 5, new Date());
		inventoryService.updateSell("Mobile01", 4, new Date());
		inventoryService.updateSell("Med01", 10, new Date());
		inventoryService.generateReport();
		inventoryService.updateSell("Food01", 5, new Date());
		inventoryService.updateSellPrice("Food01", 20.00f);
		inventoryService.updateSell("Food01", 5, new Date());
		inventoryService.updateSell("Mobile01", 4, new Date());
		inventoryService.updateSell("Med01", 10, new Date());
		inventoryService.generateReport();
	}
}
