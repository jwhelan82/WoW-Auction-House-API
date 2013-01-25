package au.wow.auctionhouse.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import au.wow.auctionhouse.comms.AuctionHouseComms;
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

	@Override
	public boolean saveAuctionHouseDataToFile(String path,
			AuctionHouseSnapshotDetails snapshotDetails) throws AuctionHouseException {
		File file = getFileForSnapshot(path, snapshotDetails);
		
		AuctionHouseComms comms = new AuctionHouseComms();
		OutputStream fos = null;
		try {
			
			if (!file.exists()) {
				if (!file.createNewFile()) {
					return false;
				}
			}
			fos = new FileOutputStream(file);
			comms.getAuctionHouseDataAsOutputStream(fos, snapshotDetails);
			
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return false;
	}
	
}
