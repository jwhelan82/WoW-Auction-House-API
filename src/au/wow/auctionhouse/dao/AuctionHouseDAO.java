package au.wow.auctionhouse.dao;

import java.util.List;

import org.json.JSONObject;

import au.wow.auctionhouse.exception.AuctionHouseException;
import au.wow.auctionhouse.model.AuctionHouseSnapshotDetails;
import au.wow.auctionhouse.model.AuctionItem;

public interface AuctionHouseDAO {

	/**
	 * Method checks whether a snapshot already exists.
	 * 
	 * @param snapshotDetails
	 * @return
	 */
	public abstract boolean isLatestSnapshot(String path, AuctionHouseSnapshotDetails snapshotDetails);

	/**
	 * Returns the Auction Items of the given IDs
	 * 
	 * @param ids
	 * @return
	 */
	public abstract List<AuctionItem> getAuctionItemsForIds(List<Integer> ids);

	/**
	 * Returns the Auction Item for the given ID
	 * 
	 * @param id
	 * @return
	 */
	public abstract AuctionItem getAuctionItemById(Integer id);

	/**
	 * Persists an Auction Item to the database
	 * 
	 * @param item
	 */
	public abstract void saveAuctionItem(AuctionItem item);

	/**
	 * Persists a list of Auction Items to the database
	 * 
	 * @param items
	 */
	public abstract void saveAuctionItems(List<AuctionItem> items);

	/**
	 * Takes a JSON Object full of auction house data and saves it to file.
	 * 
	 * @param path
	 * @param snapshotDetails
	 * @param auctionHouseData
	 * @throws AuctionHouseException
	 * @return true if a file snapshot was saved, false otherwise.
	 */

	boolean saveAuctionHouseDataToFile(String path, AuctionHouseSnapshotDetails snapshotDetails, JSONObject auctionHouseData) throws AuctionHouseException;

}