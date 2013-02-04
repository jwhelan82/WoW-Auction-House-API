package org.au.jwhelan.wowreports.persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.au.jwhelan.wowreports.model.AuctionItem;
import org.au.jwhelan.wowreports.model.Faction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * Checks for new auction data files and persists them to database. 
 * 
 * @author James Whelan
 *
 */
public class AuctionDataPersister implements Job {

	@Override
	public void execute(JobExecutionContext args) throws JobExecutionException {
		BufferedReader reader = null;
		String path = (String)args.get("path");
		String realm = (String)args.get("realm");
		
		try {
			File snapshotFile = new File(path);
			
			if (!snapshotFile.exists()) {
				throw new JobExecutionException("No snapshot file could be found at " + path);
			} 
			
			// read all lines in the file first, we can parse them into faction specific 
			// auction items later.
			reader = new BufferedReader(new FileReader(snapshotFile));
			String input = reader.readLine();
			StringBuffer lines = new StringBuffer();
			
			while(input != null) {
				lines.append(input);
				input = reader.readLine();
			}
			
			System.out.println("Reading auction data from: " + path);
			
			JSONObject auctionData = new JSONObject(lines.toString());
			
			System.out.println("Successfully read auction data.");
			
			String auctionRealm = auctionData.getJSONObject("realm").getString("name");
			System.out.println("Realm: " + auctionRealm);
			
			JSONArray allianceAuctionItems = 
					auctionData.getJSONObject("alliance").getJSONArray("auctions");

			JSONArray hordeAuctionItems = 
					auctionData.getJSONObject("horde").getJSONArray("auctions");
			
			JSONArray neutralAuctionItems = 
					auctionData.getJSONObject("neutral").getJSONArray("auctions");
			
			// TODO persist auction data
			
		} catch (JSONException e) {
			throw new JobExecutionException("Error when construcing JSON object for auction house data: " + e);
		} catch (IOException e) {
			throw new JobExecutionException("Error reading from input stream: " + e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new JobExecutionException("Error when trying to close input stream!" + e);
				}
			}
		}

	}
	
	// Returns a map of auction data per faction.
	private Map<Faction, List<AuctionItem>> getAuctionDataFromFile(final String path, final String realm) {
		Map<Faction, List<AuctionItem>> auctionData = new HashMap<Faction, List<AuctionItem>>();
		
		if (checkForNewAuctionData(path, realm)) {
			
		}
		
		return auctionData;
	}
	
	// Find the date/time of the latest update in the database,
	// then check if a newer version exists on the file system.
	private boolean checkForNewAuctionData(final String path, final String realm) {
		// TODO:
		// 1 - find latest update for the realm in the DB, get its date/time
		// 2 - construct a date from the update date/time
		// 3 - for the given file path, check if there is a file with newer last modified date
		//     (do this in separate method as getAuctionDataFromFile needs this too)
		// 4- return true/false
		return false;
	}

	// returns a File containing new auction data if available
	private File getDataFile(String path) {
		File data = new File(path);
		return data;
	}
	
}
