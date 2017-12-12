package com.store.inventorymgm.repository;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.store.inventorymgm.repository.data.Item;

public class TransactionRepositoryTest {

	private TransactionRepository transactionRepository;
	
	@Before
	public void setUp() throws Exception {
		transactionRepository = new TransactionRepository();
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testAddTransacton() throws Exception {
		Item item = new Item("testItem", 2.0f, 3.0f);
		transactionRepository.addTransacton(item, 5, new Date());
		
		assertTrue(transactionRepository.getAllTransactions(null).size() == 1);
	}
	
	@Test
	public void testGetAllTransactions() throws Exception {
		assertTrue(transactionRepository.getAllTransactions(null).size() == 0);
		Item item = new Item("testItem", 2.0f, 3.0f);
		transactionRepository.addTransacton(item, 5, new Date());
		
		assertTrue(transactionRepository.getAllTransactions(null).size() == 1);
	}
	
	@Test
	public void testGetAllTransactionsWithDateBefore() throws Exception {
		Item item = new Item("testItem", 2.0f, 3.0f);
		Date dt = new Date();
		transactionRepository.addTransacton(item, 5, dt);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		long time = cal.getTimeInMillis() - 10000;
		cal.setTimeInMillis(time);
		Date beforeDate = cal.getTime();
		
		assertTrue(transactionRepository.getAllTransactions(beforeDate).size() == 1);
	}
	
	@Test
	public void testGetAllTransactionsWithDateAfter() throws Exception {
		Item item = new Item("testItem", 2.0f, 3.0f);
		Date dt = new Date();
		transactionRepository.addTransacton(item, 5, dt);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		long time = cal.getTimeInMillis() + 10000;
		cal.setTimeInMillis(time);
		Date afterDate = cal.getTime();
		
		assertTrue(transactionRepository.getAllTransactions(afterDate).size() == 0);
	}
}
