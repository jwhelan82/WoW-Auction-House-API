package au.wow.auctionhouse.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import au.wow.auctionhouse.exception.AuctionHouseException;
import au.wow.auctionhouse.model.AuctionHouseSnapshotDetails;
import au.wow.auctionhouse.model.AuctionItem;

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
public class AuctionHouseDAOFileImpl implements AuctionHouseDAO {
	
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
	public boolean saveAuctionHouseDataToFile(String path, AuctionHouseSnapshotDetails snapshotDetails, JSONObject auctionHouseData) throws AuctionHouseException {
		try {
			
			BufferedWriter writer;
			
			// Save the snapshot as the last modified if we don't already have one.
			File file = getFileForSnapshot(path, snapshotDetails);
			if (!file.exists()) {
				file.createNewFile();
				writer = new BufferedWriter(new FileWriter(file));
			
				writer.write(auctionHouseData.toString());
			
				writer.flush();
				writer.close();
				
				return true;
			}	
			return false;
		} catch (IOException ioe) {
			throw new AuctionHouseException(ioe);
		}
	}
	
	public JSONObject readAuctionHouseSnapshotFromFile(String path) throws AuctionHouseException {
		
		BufferedReader reader = null;
		
		try {
			File snapshotFile = new File(path);
			
			if (!snapshotFile.exists()) {
				throw new AuctionHouseException("No snapshot file could be found at " + path);
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
			
			return new JSONObject(lines.toString());
		} catch (JSONException e) {
			throw new AuctionHouseException("Error when construcing JSON object for auction house data: " + e);
		} catch (IOException e) {
			throw new AuctionHouseException("Error reading from input stream: " + e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new AuctionHouseException("Error when trying to close input stream!" + e);
				}
			}
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
