package au.wow.auctionhouse.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
 * TODO use Dozer to map model classes to entities?
 * 
 * @author James Whelan
 *
 */
public class AuctionHouseDAOImpl implements AuctionHouseDAO {
	
	/* (non-Javadoc)
	 * @see au.wow.auctionhouse.dao.AuctionHouseDAO#isLatestSnapshot(java.lang.String, au.wow.auctionhouse.model.AuctionHouseSnapshotDetails)
	 */
	@Override
	public boolean isLatestSnapshot(String path, AuctionHouseSnapshotDetails snapshotDetails) {
		
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		return getFileForSnapshot(path, snapshotDetails).exists();
	}
	
	/* (non-Javadoc)
	 * @see au.wow.auctionhouse.dao.AuctionHouseDAO#getAuctionItemsForIds(java.util.List)
	 */
	@Override
	public List<AuctionItem> getAuctionItemsForIds(List<Integer> ids) {
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see au.wow.auctionhouse.dao.AuctionHouseDAO#getAuctionItemById(java.lang.Integer)
	 */
	@Override
	public AuctionItem getAuctionItemById(Integer id) {
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see au.wow.auctionhouse.dao.AuctionHouseDAO#saveAuctionItem(au.wow.auctionhouse.model.AuctionItem)
	 */
	@Override
	public void saveAuctionItem(AuctionItem item) {
		
	}
	
	/* (non-Javadoc)
	 * @see au.wow.auctionhouse.dao.AuctionHouseDAO#saveAuctionItems(java.util.List)
	 */
	@Override
	public void saveAuctionItems(List<AuctionItem> items) {
		
	}
	
	/* (non-Javadoc)
	 * @see au.wow.auctionhouse.dao.AuctionHouseDAO#saveAuctionHouseSnapshotToFile(java.lang.String, au.wow.auctionhouse.model.AuctionHouseSnapshotDetails, au.wow.auctionhouse.model.AuctionHouseSnapshot)
	 */
	@Override
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
	
	public AuctionHouseSnapshot readAuctionHouseSnapshotFromFile(String path) throws AuctionHouseException {
		
		AuctionHouseSnapshot snapshot = null;
		
		try {
			File snapshotFile = new File(path);
			
			if (!snapshotFile.exists()) {
				throw new AuctionHouseException("No snapshot file could be found at " + path);
			} 
			
			// read all lines in the file first, we can parse them into faction specific 
			// auction items later.
			BufferedReader reader = new BufferedReader(new FileReader(snapshotFile));
			String input = reader.readLine();
			List<String> lines = new ArrayList<String>();
			
			while(input != null) {
				lines.add(input);
				input = reader.readLine();
			}
		
			List<AuctionItem> allianceAuctions = new ArrayList<AuctionItem>();
			List<AuctionItem> hordeAuctions = new ArrayList<AuctionItem>();
			List<AuctionItem> neutralAuctions = new ArrayList<AuctionItem>();
			
			
			
			
		} catch (Exception e) {
			
		}
		return snapshot;
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
