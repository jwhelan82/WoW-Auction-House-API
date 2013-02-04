package org.au.jwhelan.wowreports.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that contains the auction house data retrieved from the API. 
 * 
 * @author James Whelan
 *
 */
public class AuctionHouseSnapshot  {

	private Map<Faction, List<AuctionItem>> auctionData;
	
	public AuctionHouseSnapshot() {
		auctionData = new HashMap<Faction, List<AuctionItem>>();
		auctionData.put(Faction.ALLIANCE, new ArrayList<AuctionItem>());
		auctionData.put(Faction.HORDE, new ArrayList<AuctionItem>());
		auctionData.put(Faction.NEUTRAL, new ArrayList<AuctionItem>());
	}
	
	public List<AuctionItem> getAuctions(Faction faction) {
		return auctionData.get(faction);
	}
	
	public List<AuctionItem> getHordeAuctions() {
		return auctionData.get(Faction.HORDE);
	}
	public void setHordeAuctions(List<AuctionItem> hordeAuctions) {
		getHordeAuctions().addAll(hordeAuctions);
	}
	public List<AuctionItem> getAllianceAuctions() {
		return auctionData.get(Faction.ALLIANCE);
	}
	public void setAllianceAuctions(List<AuctionItem> allianceAuctions) {
		getAllianceAuctions().addAll(allianceAuctions);	
	}
	public List<AuctionItem> getNeutralAuctions() {
		return auctionData.get(Faction.NEUTRAL);
	}
	public void setNeutralAuctions(List<AuctionItem> neutralAuctions) {
		getNeutralAuctions().addAll(neutralAuctions);
	}

	

}
