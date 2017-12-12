package com.store.inventorymgm.repository.data;

import java.util.Date;

public class Report {

	private String reportId;
	private String content;
	private Date reportDate;
	
	public Report(String reportId,
	 			  String content,
	 			  Date reportDate) {
		this.reportId = reportId;
		this.content = content;
		this.reportDate = reportDate;
	}
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
