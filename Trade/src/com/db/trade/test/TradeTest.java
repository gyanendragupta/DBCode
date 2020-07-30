package com.db.trade.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.db.trade.main.Trade;
import com.db.trade.main.TradeStore;
import com.db.trade.main.TradingException;

/**
 * @author Gyanendra Gupta <gyanendra.gupta@gmail.com>
 *
 */
public class TradeTest {
	
	/**
	 * @throws TradingException
	 * Adding lower version trades to TradeStore should throw TradingException
	 */
	@Test (expected = TradingException.class)
	public void testLowerVersionTradeIsRejected() throws TradingException {
		Trade t1 = new Trade ("T1", 2, "CP-1", "B1", "31/12/2020", "25/07/2020", 'N');
		Trade t2 = new Trade ("T1", 1, "CP-1", "B1", "31/12/2020", "25/07/2020", 'N');
		TradeStore ts = new TradeStore();
		ts.insertNewTrade(t1);
		ts.insertNewTrade(t2);
		
		
	}
	
	/**
	 * @throws TradingException
	 * Same version trade should be update the record in TradeStore
	 */
	@Test
	public void testSameVersionOverride() throws TradingException {
		Trade t1 = new Trade ("T2", 2, "CP-1", "B1", "31/12/2020", "25/07/2020", 'N');
		Trade t2 = new Trade ("T2", 2, "CP-2", "B2", "31/12/2020", "25/07/2020", 'N');
		TradeStore ts = new TradeStore();
		ts.insertNewTrade(t1);
		ts.insertNewTrade(t2);
		
		Trade t = ts.getTradeStore().get("T22");
		assertEquals("CP-2", t.getCounterPartyId());
		assertEquals("B2", t.getBookId());
		
	}
	
	/**
	 * @throws TradingException
	 * Trades having maturity date less than today should not be added to TradeStore
	 */
	@Test
	public void testMaturityDateLessThanToday() throws TradingException {
		Trade t1 = new Trade ("T3", 2, "CP-1", "B1", "25/06/2020", "25/07/2020", 'N');
		TradeStore ts = new TradeStore();
		ts.insertNewTrade(t1);
		assertNull(ts.getTradeStore().get("T32"));
	}
	
	/**
	 * @throws TradingException
	 * Store should automatically update the expired flag if in a store the trade crosses the maturity date
	 */
	@Test 
	public void testExpiredFlag() throws TradingException {
		Trade t1 = new Trade ("T4", 1, "CP-1", "B1", "27/07/2020", "25/07/2020", 'N');
		Trade t2 = new Trade ("T5", 2, "CP-1", "B1", "27/07/2020", "25/07/2020", 'N');
		
		TradeStore ts = new TradeStore();
		ts.insertNewTrade(t1);
		ts.insertNewTrade(t2);
		ts.updateExpiredFlag();
		assertEquals('Y', ts.getTradeStore().get("T41").getExpired());
		assertEquals('Y', ts.getTradeStore().get("T52").getExpired());
		
	}
	
}


