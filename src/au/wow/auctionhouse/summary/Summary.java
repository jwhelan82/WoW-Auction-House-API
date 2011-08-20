package au.wow.auctionhouse.summary;

import java.util.Collection;

import au.wow.auctionhouse.model.AuctionItem;

public interface Summary<E extends SummaryType> {

	Collection<E> getSummaryItems();
	void addItem(AuctionItem item);
	void summerise();
	void addToSummaryData(UpdateSummaryData data);
}
