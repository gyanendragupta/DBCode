package com.db.trade.main;

/**
 * @author Gyanendra Gupta <gyanendra.gupta@gmail.com>
 * Trade class representing a trade entry
 */
public class Trade {
	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private String maturityDate;
	private String createdDate;
	private char expired;
	
	public Trade (String tradeId, int version, String counterPartyId, String bookId, String maturityDate, String createdDate, char expired) {
		setTradeId(tradeId);
		setVersion(version);
		setCounterPartyId(counterPartyId);
		setBookId(bookId);
		setMaturityDate(maturityDate);
		setCreatedDate(createdDate);
		setExpired(expired);
	}

	/**
	 * @return the tradeId
	 */
	public String getTradeId() {
		return tradeId;
	}

	/**
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the counterPartyId
	 */
	public String getCounterPartyId() {
		return counterPartyId;
	}

	/**
	 * @param counterPartyId the counterPartyId to set
	 */
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the maturityDate
	 */
	public String getMaturityDate() {
		return maturityDate;
	}

	/**
	 * @param maturityDate the maturityDate to set
	 */
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the expired
	 */
	public char getExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(char expired) {
		this.expired = expired;
	}

}
