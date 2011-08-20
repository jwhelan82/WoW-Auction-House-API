package au.wow.auctionhouse.summary;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import au.wow.auctionhouse.comms.AuctionHouseComms;
import au.wow.auctionhouse.entity.UpdateFactionSummary;
import au.wow.auctionhouse.model.AuctionHouseSnapshot;

public class SummaryTestClass {

	static String FILEPATH = "C:\\Share\\ahouse\\sample-data\\wow\\auction_house\\snapshots\\us\\Saurfang\\16-08-11\\1313416992000";
	static int UPDATE_ID = 1000;

	public static void main(String[] args) throws IOException, JSONException {
		AuctionHouseComms comms = new AuctionHouseComms();
		JSONObject json = comms.getJSONfromFile(FILEPATH);
		AuctionHouseSnapshot snapshot = comms.convertJSONToAuctionHouseSnapshot(json);
		
		Summariser sum = new Summariser(UPDATE_ID);
		UpdateSummaryData data = sum.createSummary(snapshot);
		List<UpdateFactionSummary> summaries = data.getSnapshotFactionSummaries();
		int grandTotal = 0;
		for (UpdateFactionSummary summary : summaries) {
			System.out.println("There are " + summary.getItemTotal() + " items for " + summary.getFaction().name());
			grandTotal += summary.getItemTotal();
		}
		System.out.println();
		System.out.println("There are " + grandTotal + " items in total");
	}

}
