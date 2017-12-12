package com.store.inventorymgm.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.store.inventorymgm.repository.data.Report;

public class ReportRepository {

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	   
	private Set<Report> reportSet;
	
	public ReportRepository() {
		reportSet = new HashSet<>();
	}
	
	public void addReport(String content) {
		Date dt = new Date();
		DATE_TIME_FORMAT.format(dt);
		Report report = new Report("report-" + DATE_TIME_FORMAT.format(dt), content, dt);
		reportSet.add(report);
	}
	
	public Report getLastReport() {
		Report report = null;
		if (!reportSet.isEmpty()) {
			report = reportSet.stream().sorted((s1, s2) -> s2.getReportDate().compareTo(s1.getReportDate())).findFirst()
					.get();
		}
		return report;
	}
}
