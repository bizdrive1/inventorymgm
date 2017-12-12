package com.store.inventorymgm.repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReportRepositoryTest {

	private ReportRepository reportRepository;
	
	@Before
	public void setUp() throws Exception {
		reportRepository = new ReportRepository();
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testAddReport() throws Exception {
		reportRepository.addReport("report1");
		assertNotNull(reportRepository.getLastReport());		
	}
	
	@Test
	public void testGetLastReport() throws Exception {
		reportRepository.addReport("report1");
		Thread.sleep(2000);
		reportRepository.addReport("report2");
		assertEquals(reportRepository.getLastReport().getContent(), "report2");		
	}
	
	@Test
	public void testGetLastReportNoReport() throws Exception {
		assertNull(reportRepository.getLastReport());		
	}
}
