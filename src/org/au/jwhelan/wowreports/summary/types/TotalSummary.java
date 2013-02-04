package org.au.jwhelan.wowreports.summary.types;

import java.math.BigInteger;

import org.au.jwhelan.wowreports.summary.SummaryType;


public class TotalSummary implements SummaryType {
	int itemTotal;
	BigInteger totalBuyout = BigInteger.ZERO;
	BigInteger averageBuyout = BigInteger.ZERO;
	
	public BigInteger getTotalBuyout() {
		return totalBuyout;
	}
	public void setTotalBuyout(BigInteger totalBuyout) {
		this.totalBuyout = totalBuyout;
	}
	public int getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(int itemTotal) {
		this.itemTotal = itemTotal;
	}
	public BigInteger getAverageBuyout() {
		return averageBuyout;
	}
	public void setAverageBuyout(BigInteger averageBuyout) {
		this.averageBuyout = averageBuyout;
	}
	public String toString() {
		return "There are " + itemTotal + " items, with a gold total of " + totalBuyout + " and an average buyout price of " + averageBuyout;
	}
}
