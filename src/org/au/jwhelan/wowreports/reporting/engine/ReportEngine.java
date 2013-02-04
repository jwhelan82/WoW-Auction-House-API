package org.au.jwhelan.wowreports.reporting.engine;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.au.jwhelan.wowreports.reporting.GeneratedReport;
import org.au.jwhelan.wowreports.reporting.Report;
import org.au.jwhelan.wowreports.reporting.User;

/**
 * Runs reports against database, creates
 * auction alerts and delivers them.
 * 
 * @author James Whelan
 *
 */
// TODO figure out how this class should be executed - 
// e.g. main method, quartz job etc.  For now it's a main method.

// TODO will this one class be responsible for running analysis on all realms
// or just one?

// TODO One possible way would be to move the main method out to another
// class, have this class implement runnable, then create a bunch of these
// classes on startup 
public class ReportEngine {

	public static void main(String [] args) {
		new ReportEngine().go();
	}

	private void go() {
		
		// TODO perhaps make the method constantly check for 
		// new data and block until found - maybe listen for JMS message?
		if (hasNewAuctionHouseData()) {
			List<User> users = getUsers();
			List<GeneratedReport> generatedReports = generateReports();	
		}
		
	}

	// TODO implement me for real
	private boolean hasNewAuctionHouseData() {
		return false;
	}

	// TODO implement me for real
	private List<User> getUsers() {
		return Collections.EMPTY_LIST;
	}
	
	// TODO implement me for real
	private List<GeneratedReport> generateReports() {
		List<GeneratedReport> generatedReports = Collections.EMPTY_LIST;
		Set<Report> reports = getReportDefinitions();
		
		return generatedReports;
	}

	// TODO implement me for real - 
	private Set<Report> getReportDefinitions() {
		return Collections.EMPTY_SET;
	}
}
