package com.db.trade.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Gyanendra Gupta <gyanendra.gupta@gmail.com>
 * This is class implements the functionality of TradeStore 
 */
public class TradeStore {
	
	private HashMap<String, Trade> tradeStore = new HashMap<>(1000);
	private static String todayDate = "25/07/2020";
	

	/**
	 * @return the todayDate
	 */
	public static String getTodayDate() {
		return todayDate;
	}


	/**
	 * @param todayDate the todayDate to set
	 */
	public static void setTodayDate(String todayDate) {
		TradeStore.todayDate = todayDate;
	}
	
	/**
	 * @return the tradeStore
	 */
	public HashMap<String, Trade> getTradeStore() {
		return tradeStore;
	}
	
	
	/**
	 * @param t trade to be added in TradeStore
	 * - Reject the trade if the maturity date is less than today's date
	 * - Check if the trade already exist in the system based on TradeId and Version as Key
	 * 		-if version is lower than existing version trade will be rejected
	 * 		-if version is equal to existing version trade will be updated
	 * @throws TradingException 
	 */
	public void insertNewTrade(Trade t) throws TradingException {
		if (isMaturydateDateValid(t)) {
			if(!isLowerVersionTrade(t)) {
				String key = t.getTradeId() + String.valueOf(t.getVersion());
				getTradeStore().put(key, t);
			} else {
				throw new TradingException ("Higher version trade already exist");
			}
		}
		
		
	}
	
	/**
	 * @param t trade to be added in TradeStore
	 * @return boolean isMaturydateDateValid
	 * Return false if the maturity date is less than today's date
	 */
	public boolean isMaturydateDateValid(Trade t) {
		boolean isMaturityDateValid = true;
		String maturityDate = t.getMaturityDate();
		//System.out.println("The maturityDate is: " + maturityDate);
		
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		Date md = null; 
		Date today = null;
		try {
			md = sdformat.parse(maturityDate);
			today = sdformat.parse(sdformat.format(new Date()));
		} catch (ParseException e) {
			//System.out.println("Error parsing maturity date");
			e.printStackTrace();
		}
		
		if (md.compareTo(today) < 0) {
			//System.out.println("Maturity date is less than today");
			isMaturityDateValid = false;
		}
		return isMaturityDateValid;
		
	}
	
	/**
	 * @param t trade to be added in TradeStore
	 * @return boolean isLowerVersionTrade
	 * return true if there exist a higher version trade in TradeStore
	 */
	public boolean isLowerVersionTrade(Trade t) {
		boolean isLowerVersionTrade = false;
		String HigherVersionTradeKey = t.getTradeId() + String.valueOf(t.getVersion()+1);
		if (getTradeStore().get(HigherVersionTradeKey) != null){
			isLowerVersionTrade = true;
		}
		//System.out.println("Higher version trade not found");
		return isLowerVersionTrade;
	}
	
	
	/**
	 * update the expired flag to Y for all trades having maturity date less than today
	 * This method should be configured to run daily
	 */
	public void updateExpiredFlag() {
		getTradeStore().entrySet().stream().filter(new MaturyDateLessThanToday()).forEach(t -> t.getValue().setExpired('Y'));
		
	}
}

/**
 * Predicate to filter Trades having maturity date less than today
 *
 */
class MaturyDateLessThanToday implements Predicate<Map.Entry<String, Trade>> {

	@Override
	public boolean test(Map.Entry<String, Trade> entry) {
		boolean isMaturyDateLessThanToday = false;
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		Date md = null; 
		Date today = null;		
		String maturityDate = entry.getValue().getMaturityDate();
		try {
			md = sdformat.parse(maturityDate);
			today = sdformat.parse(TradeStore.getTodayDate());
		} catch (ParseException e) {
			//System.out.println("MaturyDateLessThanToday :: Error parsing maturity date");
			e.printStackTrace();
		}
		if (md.compareTo(today) < 0) {
			isMaturyDateLessThanToday = true;
		}
		return isMaturyDateLessThanToday;
	}
}
