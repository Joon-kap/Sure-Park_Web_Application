package com.ajou.cmu.reservation;

public class Reservation {
	private int revSeq;
	private String revTime;
	private String revId;
	
	public Reservation(){}

	public int getRevSeq() {
		return revSeq;
	}

	public void setRevSeq(int revSeq) {
		this.revSeq = revSeq;
	}

	public String getRevTime() {
		return revTime;
	}

	public void setRevTime(String revTime) {
		this.revTime = revTime;
	}

	public String getRevId() {
		return revId;
	}

	public void setRevId(String revId) {
		this.revId = revId;
	}
	
	

	
}
