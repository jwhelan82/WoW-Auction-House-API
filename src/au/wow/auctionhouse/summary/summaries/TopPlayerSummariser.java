package au.wow.auctionhouse.summary.summaries;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import au.wow.auctionhouse.model.AuctionItem;
import au.wow.auctionhouse.summary.Summary;
import au.wow.auctionhouse.summary.SummaryType;
import au.wow.auctionhouse.summary.types.TopPlayerSummary;

/**
 * The Top player summariser will group items into different players and create 
 * stats for each player. 
 * 
 * A couple of comparators are available for different types of 'topness'
 */
public class TopPlayerSummariser implements Summary<TopPlayerSummary> {

	Map<String, TopPlayerSummary> players = new HashMap<String, TopPlayerSummary>();
	
	@Override
	public void addItem(AuctionItem item) {
		String player = item.getOwner();
		
		if (!players.containsKey(player)) {
			TopPlayerSummary newSummary = new TopPlayerSummary();
			newSummary.setPlayerName(player);
			players.put(player, newSummary);
		}
		
		// do the summarising
		TopPlayerSummary summary = players.get(player);
		summary.setNumberOfAuctions(summary.getNumberOfAuctions() + 1);
		summary.setNumberOfAuctionItems(summary.getNumberOfAuctionItems() + item.getQuantity());
		summary.setTotalBuyout(summary.getTotalBuyout().add(BigInteger.valueOf(item.getBuyoutPrice())));
	}

	@Override
	public Collection<TopPlayerSummary> getSummaryItems() {
		return players.values();
	}

	@Override
	public void summerise() {
		// find the average buyout
		for (TopPlayerSummary player : players.values()) {
			player.setAverageBuyout(player.getTotalBuyout().divide(BigInteger.valueOf(player.getNumberOfAuctions())));
		}
	}
	
	/**
	 * Sorts from the most auction to the least. 
	 */
	public static class MostAuctionsComparator implements Comparator<SummaryType> {

		@Override
		public int compare(SummaryType p1, SummaryType p2) {
			return ((TopPlayerSummary)p2).getNumberOfAuctions()
				.compareTo(((TopPlayerSummary)p1).getNumberOfAuctions());
		}
	}
	
	/**
	 * Sorts from the highest amount of potential sales to the least. 
	 */
	public static class HighestBuyoutComparator implements Comparator<SummaryType> {

		@Override
		public int compare(SummaryType p1, SummaryType p2) {
			return ((TopPlayerSummary)p2).getTotalBuyout()
				.compareTo(((TopPlayerSummary)p1).getTotalBuyout());
		}
	}
}
