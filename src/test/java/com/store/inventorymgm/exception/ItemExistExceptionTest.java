package com.store.inventorymgm.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemExistExceptionTest {

	private ItemExistException exception;
	
	@Before
	public void setUp() throws Exception {
		exception = new ItemExistException("TestItem");
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
    public void testItemExistException() {
        assertNotNull(exception);
        assertNotNull(exception.getMessage());
    }
}
