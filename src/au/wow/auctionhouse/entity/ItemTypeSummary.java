package au.wow.auctionhouse.entity;

import au.wow.auctionhouse.model.Faction;
import au.wow.auctionhouse.summary.SummaryType;

public class ItemTypeSummary implements SummaryType {
	// TODO hibernate object for the item table
	Faction faction;
	int itemId;
	// etc... 
}
