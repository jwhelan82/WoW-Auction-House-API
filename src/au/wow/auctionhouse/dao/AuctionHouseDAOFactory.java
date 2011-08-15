package au.wow.auctionhouse.dao;

public class AuctionHouseDAOFactory {

	public enum DAOType {
		FILE_STORE,
		DATABASE_STORE
	};
	
	public static AuctionHouseDAO getInstance(DAOType type) {
		switch (type) {
		case FILE_STORE:
			return new AuctionHouseDAOFileImpl();
		case DATABASE_STORE:
			return new AuctionHouseDatabaseImpl();
		default: 
			throw new NullPointerException("The type of dao that you are after does not appear to be supported");
			// TODO throw proper error
		}
	}
	
}
