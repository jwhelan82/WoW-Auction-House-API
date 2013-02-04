package org.au.jwhelan.wowreports.reporting;

import java.util.Date;

/**
 * Generated by the report engine to hold the 
 * report output from the evaluations.
 * 
 * @author James Whelan
 *
 */
public class GeneratedReport {
	
	private String reportOutput; // TODO may want to make an object in future to represent this
	private String realm;
	private Date date;

	public GeneratedReport(String reportOutput, String realm, Date date) {
		this.reportOutput = reportOutput;
		this.realm = realm;
		this.date = date;
	}

	public String getReportOutput() {
		return reportOutput;
	}
	public String getRealm() {
		return realm;
	}
	public Date getDate() {
		return date;
	}
}