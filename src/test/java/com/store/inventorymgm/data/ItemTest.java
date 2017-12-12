package com.store.inventorymgm.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.store.inventorymgm.repository.data.Item;

public class ItemTest {
	
	private Item item;
	
	@Before
	public void setUp() throws Exception {
		item = new Item("testItem", 2.0f, 5.0f);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
    public void testItem() throws Exception {

        item.setQuantity(10);
        assertEquals("testItem", item.getItemName());
        assertTrue(item.getCostPrice() == 2.0f);
        assertTrue(item.getSellingPrice() == 5.0f);
        assertTrue(item.getQuantity() == 10);
    }
	
	@Test
    public void testItemSetters() throws Exception {

        item.setQuantity(15);
        item.setItemName("newName");
        item.setCostPrice(3.5f);
        item.setSellingPrice(9.5f);

        assertEquals("newName", item.getItemName());
        assertTrue(item.getCostPrice() == 3.5f);
        assertTrue(item.getSellingPrice() == 9.5f);
        assertTrue(item.getQuantity() == 15);
    }
}
