package org.au.jwhelan.wowreports.summary.types;

import java.math.BigInteger;

import org.au.jwhelan.wowreports.summary.SummaryType;


public class TopPlayerSummary implements SummaryType {

	String playerName;
	Integer numberOfAuctions = 0;
	Integer numberOfAuctionItems = 0;
	BigInteger totalBuyout = BigInteger.ZERO;
	BigInteger averageBuyout = BigInteger.ZERO;
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Integer getNumberOfAuctions() {
		return numberOfAuctions;
	}
	public void setNumberOfAuctions(Integer numberOfAuctions) {
		this.numberOfAuctions = numberOfAuctions;
	}
	public BigInteger getTotalBuyout() {
		return totalBuyout;
	}
	public void setTotalBuyout(BigInteger totalBuyout) {
		this.totalBuyout = totalBuyout;
	}
	public BigInteger getAverageBuyout() {
		return averageBuyout;
	}
	public void setAverageBuyout(BigInteger averageBuyout) {
		this.averageBuyout = averageBuyout;
	}
	public Integer getNumberOfAuctionItems() {
		return numberOfAuctionItems;
	}
	public void setNumberOfAuctionItems(Integer numberOfAuctionItems) {
		this.numberOfAuctionItems = numberOfAuctionItems;
	}
}
