package org.au.jwhelan.wowreports.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.au.jwhelan.wowreports.model.Duration;



/**
 * The persistent class for the auctions database table.
 * 
 */
@Entity
@Table(name="auctions")
public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuctionPK id;

	@Column(name="auction_house")
	private String auctionHouse;

	private BigDecimal bid;

	private BigDecimal buyout;

	private BigDecimal quantity;

	@Column(name="time_left")
	@Enumerated(EnumType.STRING)
	private Duration timeLeft;

	//uni-directional many-to-one association to Update
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_id")
	private AuctionHouseUpdate update;

	//uni-directional many-to-one association to Item
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;

	//uni-directional many-to-one association to Player
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="player_id")
	private Player player;

    public Auction() {
    }

	public AuctionPK getId() {
		return this.id;
	}

	public void setId(AuctionPK id) {
		this.id = id;
	}
	
	public String getAuctionHouse() {
		return this.auctionHouse;
	}

	public void setAuctionHouse(String auctionHouse) {
		this.auctionHouse = auctionHouse;
	}

	public BigDecimal getBid() {
		return this.bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public BigDecimal getBuyout() {
		return this.buyout;
	}

	public void setBuyout(BigDecimal buyout) {
		this.buyout = buyout;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Duration getTimeLeft() {
		return this.timeLeft;
	}

	public void setTimeLeft(Duration timeLeft) {
		this.timeLeft = timeLeft;
	}

	public AuctionHouseUpdate getUpdate() {
		return this.update;
	}

	public void setUpdate(AuctionHouseUpdate update) {
		this.update = update;
	}
	
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}