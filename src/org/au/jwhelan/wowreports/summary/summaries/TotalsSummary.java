package org.au.jwhelan.wowreports.summary.summaries;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.au.jwhelan.wowreports.model.AuctionItem;
import org.au.jwhelan.wowreports.summary.Summary;
import org.au.jwhelan.wowreports.summary.types.TotalSummary;


public class TotalsSummary implements Summary<TotalSummary> {
	TotalSummary details;
	
	public TotalsSummary() {
		details = new TotalSummary();
	}
	
	@Override
	public void addItem(AuctionItem item) {
		details.setItemTotal(details.getItemTotal() + 1);
		BigInteger currentBuyout = details.getTotalBuyout();
		details.setTotalBuyout(currentBuyout.add(BigInteger.valueOf(item.getBuyoutPrice())));
	}

	@Override
	public Collection<TotalSummary> getSummaryItems() {
		List<TotalSummary> ret = new LinkedList<TotalSummary>();
		ret.add(details);
		return ret;
	}

	@Override
	public void summerise() {
		details.setAverageBuyout(
				details.getTotalBuyout().divide(BigInteger.valueOf(details.getItemTotal()))
			);
	}
}
