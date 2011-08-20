package au.wow.auctionhouse.summary;

import java.util.LinkedList;
import java.util.List;

import au.wow.auctionhouse.model.AuctionHouseSnapshot;
import au.wow.auctionhouse.model.AuctionItem;
import au.wow.auctionhouse.model.Faction;

public class Summariser {
	int updateId;
	
	public Summariser(int updateId) {
		this.updateId = updateId;
	}
	
	public UpdateSummaryData createSummary(AuctionHouseSnapshot snapshot) {
		// go through each item and add it to the different summary parts
		// each summary part does it's own thing
		List<Summary> allianceSummary = createFactionSummary(snapshot.getAllianceAuctions(), getSummarisers(Faction.ALLIANCE));
		List<Summary> hordeSummary = createFactionSummary(snapshot.getHordeAuctions(), getSummarisers(Faction.HORDE));
		List<Summary> neutralSummary = createFactionSummary(snapshot.getNeutralAuctions(), getSummarisers(Faction.NEUTRAL));

		UpdateSummaryData resultData = new UpdateSummaryData();
		addToSummaryData(allianceSummary, resultData);
		addToSummaryData(hordeSummary, resultData);
		addToSummaryData(neutralSummary, resultData);
		
		return resultData;
	}
	
	private void addToSummaryData(List<Summary> factionData, UpdateSummaryData data) {
		for (Summary summary: factionData) {
			summary.addToSummaryData(data);
		}
	}
	
	private List<Summary> createFactionSummary(List<AuctionItem> items, List<Summary> summarisers) {
		for (AuctionItem item : items) {
			for (Summary summary: summarisers) {
				summary.addItem(item);
			}
		}

		for (Summary summary: summarisers) {
			summary.summerise();
		}
		
		return summarisers;
	}
	
	private List<Summary> getSummarisers(Faction faction) {
		List<Summary> retVal = new LinkedList<Summary>();
		retVal.add(new UpdateSummary(updateId, faction));
		retVal.add(new PlayerItemSummariser(updateId));
		retVal.add(new ItemTypeSummariser(updateId));
		
		return retVal;
	}
}
