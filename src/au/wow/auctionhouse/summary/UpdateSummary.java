package au.wow.auctionhouse.summary;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import au.wow.auctionhouse.entity.UpdateFactionSummary;
import au.wow.auctionhouse.model.AuctionItem;
import au.wow.auctionhouse.model.Faction;

public class UpdateSummary implements Summary<UpdateFactionSummary> {
	UpdateFactionSummary details;
	
	public UpdateSummary(int updateId, Faction faction) {
		details = new UpdateFactionSummary();
		details.setUpdateId(updateId);
		details.setFaction(faction);
	}
	
	@Override
	public void addItem(AuctionItem item) {
		details.setItemTotal(details.getItemTotal() + 1);
	}

	@Override
	public Collection<UpdateFactionSummary> getSummaryItems() {
		List<UpdateFactionSummary> ret = new LinkedList<UpdateFactionSummary>();
		ret.add(details);
		return ret;
	}

	@Override
	public void summerise() {
		// nothing to do here
	}

	@Override
	public void addToSummaryData(UpdateSummaryData data) {
		data.getSnapshotFactionSummaries().addAll(getSummaryItems());
	}

}
