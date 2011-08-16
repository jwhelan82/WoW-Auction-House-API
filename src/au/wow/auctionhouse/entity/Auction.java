package au.wow.auctionhouse.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


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
	private String timeLeft;

	//uni-directional many-to-one association to Update
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_id")
	private Update update;

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

	public String getTimeLeft() {
		return this.timeLeft;
	}

	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}

	public Update getUpdate() {
		return this.update;
	}

	public void setUpdate(Update update) {
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