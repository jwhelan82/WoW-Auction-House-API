package au.wow.auctionhouse.comms;

public interface AuctionHouseConstants {
	final String DEFAULT_REGION = "us";
	final String DEFAULT_HOST = "http://REGION.battle.net/api/wow/auction/data/REALM";
	final String DEFAULT_REALM = "Saurfang";
	final String AUCTION_URL = DEFAULT_HOST + DEFAULT_REALM;
	final String GET = "GET";
	final String DEFAULT_CHAR_ENCODING = "UTF-8";
	
	final String DEFAULT_FILEPATH = "C:\\wow\\auction_house\\snapshots";
	
	final String[] REALM_LIST = {
			"Saurfang",
			"Dreadmaul"
	};
}
