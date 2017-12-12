package com.store.inventorymgm.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.store.inventorymgm.repository.data.Item;
import com.store.inventorymgm.repository.data.Transaction;

public class TransactionTest {
	
	private Transaction transaction;
	private Date dt = new Date();
	private Item item = new Item("itemName", 2.0f, 3.0f);
	
	@Before
	public void setUp() throws Exception {
		transaction = new Transaction(item, dt);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
    public void testTransaction() throws Exception {

        assertSame(transaction.getItem(), item);
        assertEquals(transaction.getTransactionDate(), dt);
    }
	
	@Test
    public void testTransactionSetters() throws Exception {

		Item newItem = new Item("new Item", 3.0f, 40f);
		transaction.setItem(newItem);
		Date date = new Date();
		transaction.setTransactionDate(date);
        assertSame(transaction.getItem(), newItem);
        assertEquals(transaction.getTransactionDate(), date);
    }
}
