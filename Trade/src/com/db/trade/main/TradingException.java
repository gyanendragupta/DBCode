package com.db.trade.main;

/**
 * @author Gyanendra Gupta <gyanendra.gupta@gmail.com>
 *
 */
public class TradingException extends Exception {

	private static final long serialVersionUID = 2751408400513439131L;

	public TradingException(String message) {
        super("TradingException : " + message);
    }

}
