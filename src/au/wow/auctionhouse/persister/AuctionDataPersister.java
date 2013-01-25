package au.wow.auctionhouse.persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
			
			String realm = auctionData.getJSONObject("realm").getString("name");
			System.out.println("Realm: " + realm);
			
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

}
