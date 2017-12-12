package com.store.inventorymgm.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.store.inventorymgm.repository.data.Report;

public class ReportTest {

	private Report report;
	private Date dt = new Date();
	
	@Before
	public void setUp() throws Exception {
		report = new Report("reportId", "content", dt);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
    public void testReport() throws Exception {

        assertEquals(report.getReportId(), "reportId");
        assertEquals(report.getContent(), "content");
        assertEquals(report.getReportDate(), dt);
    }
	
	@Test
    public void testReportSetters() throws Exception {

		report.setReportId("newReportId");
		report.setContent("newContent");
		Date date = new Date();
		report.setReportDate(date);
        assertEquals(report.getReportId(), "newReportId");
        assertEquals(report.getContent(), "newContent");
        assertEquals(report.getReportDate(), date);
    }
}
