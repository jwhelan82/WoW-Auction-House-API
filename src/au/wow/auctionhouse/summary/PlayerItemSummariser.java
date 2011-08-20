package au.wow.auctionhouse.summary;

import java.util.Collection;
import java.util.LinkedList;

import au.wow.auctionhouse.entity.PlayerItemSummary;
import au.wow.auctionhouse.entity.UpdateFactionSummary;
import au.wow.auctionhouse.model.AuctionItem;

public class PlayerItemSummariser implements Summary<PlayerItemSummary> {
	
	int updateId;
	
	public PlayerItemSummariser(int updateId) {
		this.updateId = updateId;
	}
	
	@Override
	public void addItem(AuctionItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<PlayerItemSummary> getSummaryItems() {
		return new LinkedList<PlayerItemSummary>();
	}

	@Override
	public void summerise() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToSummaryData(UpdateSummaryData data) {
		data.getPlayerItemSummaries().addAll(getSummaryItems());
	}

}
