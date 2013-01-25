package au.wow.auctionhouse.model;

/**
 * Class to encapsulate an auction item.
 * 
 * @author James Whelan
 *
 */
public class AuctionItem {

	private Long id;
	private Integer itemId;
	private String owner;
	private Integer quantity;
	private Integer bidPrice;
	private Integer buyoutPrice;
	private Duration timeLeft;
	private Faction faction;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Integer getBuyoutPrice() {
		return buyoutPrice;
	}
	public void setBuyoutPrice(Integer buyoutPrice) {
		this.buyoutPrice = buyoutPrice;
	}
	public Duration getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(Duration timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	public Faction getFaction() {
		return faction;
	}
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	public String toString() {
		return id.toString() + " " + itemId + " " + owner + " " + quantity + " " + bidPrice + " " + buyoutPrice + " " + timeLeft.toString();  
		
	}
}
