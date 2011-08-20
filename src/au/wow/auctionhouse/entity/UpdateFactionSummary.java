package au.wow.auctionhouse.entity;

import au.wow.auctionhouse.model.Faction;
import au.wow.auctionhouse.summary.SummaryType;

public class UpdateFactionSummary implements SummaryType {
	int updateId;
	Faction faction;
	int itemTotal;
	
	public int getUpdateId() {
		return updateId;
	}
	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}
	public Faction getFaction() {
		return faction;
	}
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	public int getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(int itemTotal) {
		this.itemTotal = itemTotal;
	}
}
