package au.wow.auctionhouse.exception;

import java.io.IOException;

public class AuctionHouseException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionHouseException() {
		super();
	}

	public AuctionHouseException(Exception e) {
		super(e);
	}
	
}
