package com.store.inventorymgm.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemOutOfStockExceptionTest {

	private ItemOutOfStockException exception;
	
	@Before
	public void setUp() throws Exception {
		exception = new ItemOutOfStockException("TestItem", 10);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
    public void testItemOutOfStockException() {
        assertNotNull(exception);
        assertNotNull(exception.getMessage());
    }
}
