package org.au.jwhelan.wowreports.summary;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.au.jwhelan.wowreports.comms.AuctionHouseComms;
import org.au.jwhelan.wowreports.model.AuctionHouseSnapshot;
import org.au.jwhelan.wowreports.summary.summaries.TopPlayerSummariser;
import org.au.jwhelan.wowreports.summary.summaries.TotalsSummary;
import org.au.jwhelan.wowreports.summary.types.TopPlayerSummary;
import org.json.JSONException;
import org.json.JSONObject;


public class SummaryTestClass {

	static String FILEPATH = "C:\\Share\\ahouse\\sample-data\\wow\\auction_house\\snapshots\\us\\Saurfang\\16-08-11\\1313416992000";
	static int UPDATE_ID = 1000;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, JSONException {
		AuctionHouseComms comms = new AuctionHouseComms();
		JSONObject json = comms.getJSONfromFile(FILEPATH);
		AuctionHouseSnapshot snapshot = comms.convertJSONToAuctionHouseSnapshot(json);
		
		Summariser sum = new Summariser();
		List<SummaryType> data = sum.createSummary(snapshot.getAllianceAuctions(), new TotalsSummary());

		for (SummaryType summary : data) {
			System.out.println(summary.toString());
		}
		
		List<SummaryType> playerData = sum.createSummary(snapshot.getAllianceAuctions(), new TopPlayerSummariser());
		Collections.sort(playerData, new TopPlayerSummariser.MostAuctionsComparator());
		
		System.out.println("Top 10 players ordered by number of auctions: ");
		for (int i = 0; i < 10; i++) {
			TopPlayerSummary player = (TopPlayerSummary) playerData.get(i);
			System.out.printf("%2d %15s %d %d\n", i + 1, player.getPlayerName(), player.getNumberOfAuctions(), player.getTotalBuyout());
		}
		
		Collections.sort(playerData, new TopPlayerSummariser.HighestBuyoutComparator());
		System.out.println("\nTop 10 players ordered by total buyout price: ");
		for (int i = 0; i < 10; i++) {
			TopPlayerSummary player = (TopPlayerSummary) playerData.get(i);
			System.out.printf("%2d %15s %3d %d\n", i + 1, player.getPlayerName(), player.getNumberOfAuctions(), player.getTotalBuyout());
		}
	}
}
