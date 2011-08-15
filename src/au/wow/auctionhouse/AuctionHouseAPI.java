package au.wow.auctionhouse;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import au.wow.auctionhouse.comms.AuctionHouseComms;
import au.wow.auctionhouse.dao.AuctionHouseDAO;
import au.wow.auctionhouse.dao.AuctionHouseDAOFileImpl;
import au.wow.auctionhouse.model.AuctionHouseSnapshotDetails;

/**
 * Simple class to retrieve auction house snapshots and save them for later use.

 * @author James Whelan
 * 
 * @deprecated
 */
public class AuctionHouseAPI {

	final String DEFAULT_FILEPATH = "C:\\wow\\auction_house\\snapshots";
	
	public void run(String[] args) {

		try {
			
			// TODO add some code to make this run at some regular interval
			String path = args.length == 1? args[0] : DEFAULT_FILEPATH;
			
			JSONObject snapshot = null;
			Date today = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
			path += "\\" + df.format(today);
			
			AuctionHouseComms ahComms = new AuctionHouseComms();
			//ahComms.setProxyHost("192.168.105.2");
			//ahComms.setProxyPort(3128);
			AuctionHouseSnapshotDetails snapshotDetails = ahComms.getAuctionHouseSnapshotDetails();
			AuctionHouseDAO dao = new AuctionHouseDAOFileImpl();
			boolean created = false;
			
			if (!dao.isLatestSnapshot(path, snapshotDetails)) {
				snapshot = ahComms.getAuctionHouseData(snapshotDetails);
				created = dao.saveAuctionHouseDataToFile(path, snapshotDetails, snapshot);
			}
			
			if (created) {
				System.out.print("Saved auction file: " + path + "\\" + snapshotDetails.getLastModified());
			} else {
				System.out.print("Snapshot " + path + "\\" + snapshotDetails.getLastModified() + " already exists.");
			}
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new AuctionHouseAPI().run(args);
	}

}
