package au.wow.auctionhouse.model;

/**
 * Class to encapsulate a snapshot of the auction house
 * 
 * @author James Whelan
 *
 */
public class AuctionHouseSnapshotDetails {
	
	private String url;
	private Long lastModified;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getLastModified() {
		return lastModified;
	}
	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

}
