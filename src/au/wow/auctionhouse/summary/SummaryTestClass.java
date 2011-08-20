package au.wow.auctionhouse.summary;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import au.wow.auctionhouse.comms.AuctionHouseComms;
import au.wow.auctionhouse.model.AuctionHouseSnapshot;
import au.wow.auctionhouse.summary.summaries.TotalsSummary;

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
	}
}
