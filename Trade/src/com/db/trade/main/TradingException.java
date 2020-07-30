package com.db.trade.main;

public class TradingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2751408400513439131L;

	public TradingException(String message) {
        super("TradingException : " + message);
    }

}
