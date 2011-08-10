package au.wow.auctionhouse.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.wow.auctionhouse.exception.AuctionHouseException;
import au.wow.auctionhouse.model.AuctionHouseSnapshot;
import au.wow.auctionhouse.model.AuctionHouseSnapshotDetails;
import au.wow.auctionhouse.model.AuctionItem;
import au.wow.auctionhouse.model.Faction;

/**
 * This is called a DAO but really just saves to a file.
 * 
 * TODO this class needs a method to retrieve a snapshot file from disk and insert all the rows into the database.
 * 
 * TODO in future this class should be responsible for persisting auction items straight to database.
 * 
 * @author James Whelan
 *
 */
public class AuctionHouseDAO {
	
	/**
	 * Method checks whether a snapshot already exists.
	 * 
	 * @param snapshotDetails
	 * @return
	 */
	public boolean isLatestSnapshot(String path, AuctionHouseSnapshotDetails snapshotDetails) {
		
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		return getFileForSnapshot(path, snapshotDetails).exists();
	}
	
	/**
	 * Takes an AuctionHouseSnapshot and saves to a file.
	 * 
	 * Each auction house is listed in all caps followed by their auctions e.g.
	 * ALLIANCE
	 * <auction 1>
	 * <...>
	 * <auction n>
	 * HORDE
	 * <auction 1>
	 * <...>
	 * <auction n>
	 * NEUTRAL
	 * <auction 1>
	 * <...>
	 * <auction n>
	 * 
	 * So when reading in it's fairly easy to only read faction auctions you're interested in.
	 * 
	 * Format is
	 * 
	 * auction_id item_id owner quantity bid_price buyout_price timeLeft
	 *
	 * TODO auction items are saved one per line with each field separated by a whitespace.
	 * This could probably be made better/easier to deal with. 
	 * 
	 * @param path
	 * @param snapshotDetails
	 * @param snapshot
	 * @throws AuctionHouseException
	 * @return true if a file snapshot was saved, false otherwise.
	 */
	public boolean saveAuctionHouseSnapshotToFile(String path, AuctionHouseSnapshotDetails snapshotDetails, AuctionHouseSnapshot snapshot) throws AuctionHouseException {
		try {
			
			BufferedWriter writer;
			
			// Save the snapshot as the last modified if we don't already have one.
			File file = getFileForSnapshot(path, snapshotDetails);
			if (!file.exists()) {
				file.createNewFile();
				writer = new BufferedWriter(new FileWriter(file));
				
				// write auction house data to file
				for (Faction faction : Faction.values()) {
					writer.write(faction.toString());
					writer.newLine();
					List<AuctionItem> items = snapshot.getAuctions(faction);

					for (AuctionItem item : items) {
						writer.write(item.toString());
						writer.newLine();
					}
				}
				writer.flush();
				writer.close();
				
				return true;
			}	
			return false;
		} catch (IOException ioe) {
			throw new AuctionHouseException(ioe);
		}
	}
	
	/**
	 * Returns a file to be used for a snapshot.
	 *  
	 * @param path
	 * @param snapshotDetails
	 * @return
	 */
	private File getFileForSnapshot(String path, AuctionHouseSnapshotDetails snapshotDetails) {
		File dirPath = new File(path);
		return new File(dirPath.getAbsolutePath() + "\\" + snapshotDetails.getLastModified());
	}
	
}
