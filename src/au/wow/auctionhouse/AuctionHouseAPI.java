package au.wow.auctionhouse;

import au.wow.auctionhouse.comms.AuctionHouseComms;
import au.wow.auctionhouse.dao.AuctionHouseDAO;
import au.wow.auctionhouse.model.AuctionHouseSnapshot;
import au.wow.auctionhouse.model.AuctionHouseSnapshotDetails;

/**
 * Simple class to retrieve auction house snapshots and save them for later use.

 * @author James Whelan
 *
 */
public class AuctionHouseAPI {

	final String DEFAULT_FILEPATH = "C:\\wow\\auction_house\\snapshots";
	
	public void run(String[] args) {

		try {
			
			// TODO add some code to make this run at some regular interval
			String path = args.length == 1? args[0] : DEFAULT_FILEPATH;
			
			AuctionHouseSnapshot snapshot = null;
			
			AuctionHouseComms ahComms = new AuctionHouseComms();
			ahComms.setProxyHost("192.168.105.2");
			ahComms.setProxyPort(3128);
			AuctionHouseSnapshotDetails snapshotDetails = ahComms.getAuctionHouseSnapshotDetails();
			AuctionHouseDAO dao = new AuctionHouseDAO();
			boolean created = false;
			
			if (!dao.isLatestSnapshot(path, snapshotDetails)) {
				snapshot = ahComms.getAuctionHouseSnapshot(snapshotDetails);
				created = dao.saveAuctionHouseSnapshotToFile(path, snapshotDetails, snapshot);
			}
			
			if (created) {
				System.out.print("Saved auction file: " + path + "\\" + snapshotDetails.getLastModified());
			} else {
				System.out.print("Snapshot " + snapshotDetails.getLastModified() + " already exists.");
			}
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new AuctionHouseAPI().run(args);
	}

}
