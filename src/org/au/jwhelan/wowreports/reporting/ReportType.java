package org.au.jwhelan.wowreports.reporting;

/**
 * Class to define a report that is generated
 * from the auction house data.
 *   
 * @author jwhelan
 *
 */
public class ReportType {
	
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
