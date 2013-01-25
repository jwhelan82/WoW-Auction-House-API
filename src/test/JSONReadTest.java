package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Read JSON-format auction data from file
 * @author jwhelan
 *
 */
public class JSONReadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		BufferedReader reader = null;
		
		try {
			
			String path = args[0];
			
			if (args.length < 1) {
				return;
			} else if (args.length > 1) {
				System.out.println("Too many arguments.\nUsage: JSONReadTest <path_to_auction_data_file>");	
			}
			
			File snapshotFile = new File(path);
			
			if (!snapshotFile.exists()) {
				System.out.println("No snapshot file could be found at " + path);
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
			System.out.println("Found " + allianceAuctionItems.length() + " alliance auctions.");
			
			JSONArray hordeAuctionItems = 
					auctionData.getJSONObject("horde").getJSONArray("auctions");
			System.out.println("Found " + hordeAuctionItems.length() + " horde auctions.");
			
			JSONArray neutralAuctionItems = 
					auctionData.getJSONObject("neutral").getJSONArray("auctions");
			System.out.println("Found " + neutralAuctionItems.length() + " neutral auctions.");

		} catch (JSONException e) {
			System.out.println("Error when construcing JSON object for auction house data: " + e);
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Error reading from input stream: " + e);
			System.exit(-1);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("Error when trying to close input stream!" + e);
				}
			}
		}
	
	}

}
