package com.store.inventorymgm.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemNotFoundExceptionTest {

	private ItemNotFoundException exception;
	
	@Before
	public void setUp() throws Exception {
		exception = new ItemNotFoundException("TestItem");
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
    public void testItemNotFoundException() {
        assertNotNull(exception);
        assertNotNull(exception.getMessage());
    }
}
