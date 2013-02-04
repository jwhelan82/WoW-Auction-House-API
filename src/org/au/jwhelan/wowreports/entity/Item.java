package org.au.jwhelan.wowreports.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the items database table.
 * 
 */
@Entity
@Table(name="items")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_id")
	private long itemId;

	@Column(name="item_name")
	private String itemName;

    public Item() {
    }

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}