package au.wow.auctionhouse.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the auctions database table.
 * 
 */
@Embeddable
public class AuctionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="auction_id")
	private long auctionId;

	@Column(name="update_id")
	private int updateId;

    public AuctionPK() {
    }
	public long getAuctionId() {
		return this.auctionId;
	}
	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}
	public int getUpdateId() {
		return this.updateId;
	}
	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AuctionPK)) {
			return false;
		}
		AuctionPK castOther = (AuctionPK)other;
		return 
			(this.auctionId == castOther.auctionId)
			&& (this.updateId == castOther.updateId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.auctionId ^ (this.auctionId >>> 32)));
		hash = hash * prime + this.updateId;
		
		return hash;
    }
}