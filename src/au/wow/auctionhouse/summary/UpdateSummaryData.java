package au.wow.auctionhouse.summary;

import java.util.LinkedList;
import java.util.List;

import au.wow.auctionhouse.entity.ItemTypeSummary;
import au.wow.auctionhouse.entity.PlayerItemSummary;
import au.wow.auctionhouse.entity.UpdateFactionSummary;

public class UpdateSummaryData {
	List<UpdateFactionSummary> snapshotFactionSummaries;
	List<PlayerItemSummary> playerItemSummaries;
	List<ItemTypeSummary> itemTypeSummaries;
	
	public UpdateSummaryData() {
		snapshotFactionSummaries = new LinkedList<UpdateFactionSummary>();
		playerItemSummaries = new LinkedList<PlayerItemSummary>();
		itemTypeSummaries = new LinkedList<ItemTypeSummary>();
	}
	
	public List<UpdateFactionSummary> getSnapshotFactionSummaries() {
		return snapshotFactionSummaries;
	}
	public void setSnapshotFactionSummaries(
			List<UpdateFactionSummary> snapshotFactionSummaries) {
		this.snapshotFactionSummaries = snapshotFactionSummaries;
	}
	public List<PlayerItemSummary> getPlayerItemSummaries() {
		return playerItemSummaries;
	}
	public void setPlayerItemSummaries(List<PlayerItemSummary> playerItemSummaries) {
		this.playerItemSummaries = playerItemSummaries;
	}
	public List<ItemTypeSummary> getItemTypeSummaries() {
		return itemTypeSummaries;
	}
	public void setItemTypeSummaries(List<ItemTypeSummary> itemTypeSummaries) {
		this.itemTypeSummaries = itemTypeSummaries;
	}
}
