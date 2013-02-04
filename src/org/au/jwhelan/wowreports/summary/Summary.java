package org.au.jwhelan.wowreports.summary;

import java.util.Collection;

import org.au.jwhelan.wowreports.model.AuctionItem;


public interface Summary<E extends SummaryType> {

	Collection<E> getSummaryItems();
	void addItem(AuctionItem item);
	void summerise();
}
