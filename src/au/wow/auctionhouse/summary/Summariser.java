package au.wow.auctionhouse.summary;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import au.wow.auctionhouse.model.AuctionItem;

public class Summariser {
	
	/**
	 * Take a list of auction items and create a set of summary data for those items. 
	 */
	public List<SummaryType> createSummary(Collection<AuctionItem> items, Summary<? extends SummaryType> ... summaries) {
		
		List<SummaryType> retVal = new LinkedList<SummaryType>();
		for (Summary<? extends SummaryType> summary : summaries) {
			runSummary(items, summary);
			retVal.addAll(summary.getSummaryItems());
		}
		
		return retVal;
	}
	
	private void runSummary(Collection<AuctionItem> items, Summary<? extends SummaryType> summary) {
		for (AuctionItem item : items) {
			summary.addItem(item);
		}
		summary.summerise();
	}
}
