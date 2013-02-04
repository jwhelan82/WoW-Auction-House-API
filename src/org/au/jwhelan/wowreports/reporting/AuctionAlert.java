package org.au.jwhelan.wowreports.reporting;

import java.util.List;

/**
 * Instances of this class are used to store
 * a user and all generated reports that they
 * are interested in.
 * 
 * @author James Whelan
 *
 */
public class AuctionAlert {

	private User user;
	private List<GeneratedReport> reports;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<GeneratedReport> getGeneratedReports() {
		return reports;
	}
	public void addGeneratedReport(GeneratedReport report) {
		reports.add(report);
	}
}
