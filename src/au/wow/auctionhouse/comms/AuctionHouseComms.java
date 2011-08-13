package au.wow.auctionhouse.comms;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import au.wow.auctionhouse.exception.AuctionHouseException;
import au.wow.auctionhouse.model.AuctionDuration;
import au.wow.auctionhouse.model.AuctionHouseSnapshot;
import au.wow.auctionhouse.model.AuctionHouseSnapshotDetails;
import au.wow.auctionhouse.model.AuctionItem;
import au.wow.auctionhouse.model.Faction;
import au.wow.common.util.HttpUtils;

/**
 * TODO since there is a lot of information being retrieved, it is possible that 
 * this class may need to implement runnable so that it can consuming classes can
 * run instances of the class in separate threads.  Either that, or consuming classes
 * should themselves implement runnable, and create instances of this class as ThreadLocal variables.
 * 
 * TODO I'm not super keen on how this 'comms' class also does the mapping from JSON data to
 * the auction snapshot model; might be worth splitting that into its own class? 
 * 
 * TODO proxy support is fairly basic, but if people need anything other than HTTP proxy with no 
 * authentication then it isn't my problem.
 * 
 * @author James Whelan
 *
 */
public class AuctionHouseComms {

	final String DEFAULT_REGION = "us";
	final String DEFAULT_HOST = "http://REGION.battle.net/api/wow/auction/data/REALM";
	final String DEFAULT_REALM = "Saurfang";
	final String AUCTION_URL = DEFAULT_HOST + DEFAULT_REALM;
	final String GET = "GET";
	final String DEFAULT_CHAR_ENCODING = "UTF-8";
	
	private String proxyHost;
	private int proxyPort;
	
	public AuctionHouseComms() {
		proxyPort = -1;
		proxyHost = null;
	}
	
	public AuctionHouseSnapshotDetails getAuctionHouseSnapshotDetails() throws AuctionHouseException {
		return getAuctionHouseSnapshotDetails(DEFAULT_REALM);
	}
	
	public AuctionHouseSnapshotDetails getAuctionHouseSnapshotDetails(String realm) throws AuctionHouseException {
		return getAuctionHouseSnapshotDetails(DEFAULT_REGION, realm);
	}
	
	/**
	 * Get the snapshot details (url, last modified) for the auction house on the given region/realm.
	 * 
	 * @param region
	 * @param realm
	 * @return
	 * @throws AuctionHouseException
	 */
	public AuctionHouseSnapshotDetails getAuctionHouseSnapshotDetails(String region, String realm) throws AuctionHouseException {
		
		try {
			// yeah this string replacement is really lazy but I cbf making it better.
			String url = (DEFAULT_HOST).replace("REGION", region).replace("REALM", realm); 
			
			JSONObject snapshotData = getJSONFromUrl(url);
			AuctionHouseSnapshotDetails snapshot = new AuctionHouseSnapshotDetails();
			
			JSONArray files = snapshotData.getJSONArray("files");
			JSONObject dataArray = (JSONObject) files.get(0);
			
			snapshot.setUrl(dataArray.getString("url"));
			snapshot.setLastModified(dataArray.getLong("lastModified")); 
			
			return snapshot;
			
		} catch (IOException ioe) {
			throw new AuctionHouseException(ioe);
		} catch (JSONException je) {
			throw new AuctionHouseException(je);
		}
	}
	
	
	/**
	 * Returns a populated object of the auction house data.  This method should only be called
	 * after retrieving a snapshot of the auction house details and determining whether a refresh
	 * is required.  Blizzard currently only support snapshots of the auction house and not live
	 * queries.
	 * 
	 * Due to Blizzard only supporting full snapshots of the auction house (e.g. no faction-specific
	 * apis as of yet), this method will LIKELY take a fair amount of time, network connection speed, 
	 * server load, realm population, current auction house load etc.  
	 * 
	 * Auction houses on busy realms can generate snapshots of up to 5MB or more.
	 * 
	 * Use this method with caution, it should be called infrequently and should not be called
	 * in UI-centric applications unless appropriately handled (e.g. in a separate thread).
	 * 
	 * @param snapshotDetails
	 * @return
	 * @throws AuctionHouseException
	 */
	public AuctionHouseSnapshot getAuctionHouseSnapshot(AuctionHouseSnapshotDetails snapshotDetails) throws AuctionHouseException {
		
		final String auctions = "auctions";
		AuctionHouseSnapshot snapshot = new AuctionHouseSnapshot();
		
		try {
			Map<Faction, JSONArray> auctionLists = new HashMap<Faction, JSONArray>();
			
			JSONObject ahData = getJSONFromUrl(snapshotDetails.getUrl());
			
			JSONObject hordeData = ahData.getJSONObject("horde");
			JSONObject allianceData = ahData.getJSONObject("alliance");
			JSONObject neutralData = ahData.getJSONObject("neutral");
			
			auctionLists.put(Faction.HORDE, (JSONArray)hordeData.get(auctions));
			auctionLists.put(Faction.ALLIANCE, (JSONArray)allianceData.get(auctions));
			auctionLists.put(Faction.NEUTRAL, (JSONArray)neutralData.get(auctions));
			
			// for horde, ally and neutral auction lists
			for (Faction faction : auctionLists.keySet()) {
				
				List<AuctionItem> auctionItems = snapshot.getAuctions(faction);
				JSONArray auctionList = auctionLists.get(faction);
				
				// for each entry in the array, create an auction item for it
				for (int i = 0; i < auctionList.length(); i++) {
					JSONObject obj = (JSONObject)auctionList.get(i);
					AuctionItem item = new AuctionItem();
					item.setId(obj.getLong("auc"));
					item.setBuyoutPrice(obj.getInt("buyout"));
					item.setBidPrice(obj.getInt("bid"));
					item.setItemId(obj.getInt("item"));
					item.setOwner(obj.getString("owner"));
					item.setQuantity(obj.getInt("quantity"));
					item.setTimeLeft(AuctionDuration.valueOf(obj.getString("timeLeft")));
					auctionItems.add(item);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return snapshot;
	}



	/**
	 * Takes a URL and returns a JSON object based on the returned input stream data.
	 * Used to get JSON for both auction house snapshot and the details.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	private JSONObject getJSONFromUrl(String url) throws IOException, JSONException {
		
		HttpURLConnection conn = HttpUtils.getHttpConnection(url, proxyHost, proxyPort);
		String results = HttpUtils.getOutputStringFromConnection(conn);
		return new JSONObject(results);
	}
	
	/**
	 * From some XML document, create a JSON object from some embedded JSON.
	 * 
	 * @param doc
	 * @param elementName
	 * @return JSONObject 
	 */
	private JSONObject getJSONFromXml(Document doc, String elementName) {
	
		return null;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}


}