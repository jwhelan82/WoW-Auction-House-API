package au.wow.auctionhouse.dao;

import java.util.List;

import au.wow.auctionhouse.exception.AuctionHouseException;
import au.wow.auctionhouse.model.AuctionHouseSnapshot;
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
	 * Takes an AuctionHouseSnapshot and saves to a file.
	 * <br />
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
	 * <br />
	 * So when reading in it's fairly easy to only read faction auctions you're interested in.
	 * <br />
	 * Format is
	 * <br />
	 * auction_id item_id owner quantity bid_price buyout_price timeLeft
	 * <br />
	 * TODO auction items are saved one per line with each field separated by a whitespace.
	 * This could probably be made better/easier to deal with. 
	 * 
	 * @param path
	 * @param snapshotDetails
	 * @param snapshot
	 * @throws AuctionHouseException
	 * @return true if a file snapshot was saved, false otherwise.
	 */
	public abstract boolean saveAuctionHouseSnapshotToFile(String path,
			AuctionHouseSnapshotDetails snapshotDetails,
			AuctionHouseSnapshot snapshot) throws AuctionHouseException;

}