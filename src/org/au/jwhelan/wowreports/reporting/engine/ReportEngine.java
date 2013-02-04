package org.au.jwhelan.wowreports.reporting.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.au.jwhelan.wowreports.reporting.AuctionAlert;
import org.au.jwhelan.wowreports.reporting.DeliveryType;
import org.au.jwhelan.wowreports.reporting.GeneratedReport;
import org.au.jwhelan.wowreports.reporting.ReportType;
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
			Map<ReportType, GeneratedReport> generatedReports = generateReports();	
			Map<User, List<AuctionAlert>> generatedAlerts = generateAlerts(users, generatedReports);
			
		}
		
	}

	private AuctionAlert createAuctionAlert(ReportType type, User user, final Map<ReportType, GeneratedReport> generatedReports) {
		GeneratedReport generatedReport = generatedReports.get(type);
		
		// shouldn't be null, but check anyway, because manners cost nothing (well, except CPU cycles)
		if (generatedReport != null){ 
			AuctionAlert alert = new AuctionAlert();
			alert.setUser(user);
			alert.addGeneratedReport(generatedReport);
			return alert;
		}
		return null;
	}
	
	// TODO probably better to return a map of User -> List<AuctionAlert>, but that can be changed later.
	private Map<User, List<AuctionAlert>> generateAlerts(final List<User> users, final Map<ReportType, GeneratedReport> generatedReports) {
		Map<User, List<AuctionAlert>> alerts = new HashMap<User, List<AuctionAlert>>();
				
		for (User user : users) {
			
			// Generate an auction alert for each of the generated reports that
			// the user is interested in.
			for (ReportType reportType : user.getSubscribedReports()) {
				AuctionAlert alert = createAuctionAlert(reportType, user, generatedReports); 
				if (alert != null) {
					
					List<AuctionAlert> alertsForUser = alerts.get(user);
					if (alertsForUser == null) {
						alertsForUser = new ArrayList<AuctionAlert>();
						alerts.put(user, alertsForUser);
					}
					
					alertsForUser.add(alert);
				}
			}
		}
		return alerts;
	}
	
	// TODO implement me for real
	private Map<ReportType, GeneratedReport> generateReports() {
		Map<ReportType, GeneratedReport> generatedReports = Collections.EMPTY_MAP;
		Set<ReportType> reports = getReportDefinitions();
		
		for (ReportType reportType : reports) {
			// TODO run evaluations and generate a report for each and add to list.
			// TODO We should only generate reports where there is actually a subscriber
			//      for them.  The method getReportDefinitions() should filter out 
			//      any reports that aren't subscribed to.
			generatedReports.put(reportType, new GeneratedReport(null, null, null));	
		}
		
		return generatedReports;
	}
	
	// TODO implement me for real
	private Set<ReportType> getReportDefinitions() {
		return Collections.EMPTY_SET;
	}
	
	// TODO implement me for real
	private List<User> getUsers() {
		return Collections.EMPTY_LIST;
	}
	
	// TODO implement me for real
	private boolean hasNewAuctionHouseData() {
		return false;
	}

}
