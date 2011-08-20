package au.wow.auctionhouse.summary;

import java.util.Collection;
import java.util.LinkedList;

import au.wow.auctionhouse.entity.ItemTypeSummary;
import au.wow.auctionhouse.entity.PlayerItemSummary;
import au.wow.auctionhouse.model.AuctionItem;

public class ItemTypeSummariser implements Summary<ItemTypeSummary> {

	int updateId;
	
	public ItemTypeSummariser(int updateId) {
		this.updateId = updateId;
	}
	
	@Override
	public void addItem(AuctionItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<ItemTypeSummary> getSummaryItems() {
		// TODO Auto-generated method stub
		return new LinkedList<ItemTypeSummary>();
	}

	@Override
	public void summerise() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToSummaryData(UpdateSummaryData data) {
		data.getItemTypeSummaries().addAll(getSummaryItems());
	}

}
