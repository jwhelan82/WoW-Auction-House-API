package org.au.jwhelan.wowreports.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the updates database table.
 * 
 */
@Entity
@Table(name="auction_house_updates")
public class AuctionHouseUpdate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="update_id")
	private int updateId;

	private String realm;

	@Column(name="update_time")
	private int updateTime;

    public AuctionHouseUpdate() {
    }

	public int getUpdateId() {
		return this.updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public int getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}

}