package com.ajou.cmu.reservation;

public class Reservation {
	/*git test*/
	private int pReserId;
	private String pIdentifier;
	private String pReserTelno;
	private String pReserTime;
	private String pWaitYn;
	private String pCancelYn;
	private String pDesc1;
	private String pDesc2;
	private String pCreateDt;
	private String pUpdateDt;
	
	public Reservation(){}

	public int getpReserId() {
		return pReserId;
	}

	public void setpReserId(int pReserId) {
		this.pReserId = pReserId;
	}

	public String getpIdentifier() {
		return pIdentifier;
	}

	public void setpIdentifier(String pIdentifier) {
		this.pIdentifier = pIdentifier;
	}

	public String getpReserTelno() {
		return pReserTelno;
	}

	public void setpReserTelno(String pReserTelno) {
		this.pReserTelno = pReserTelno;
	}

	public String getpReserTime() {
		return pReserTime;
	}

	public void setpReserTime(String pReserTime) {
		this.pReserTime = pReserTime;
	}

	public String getpWaitYn() {
		return pWaitYn;
	}

	public void setpWaitYn(String pWaitYn) {
		this.pWaitYn = pWaitYn;
	}

	public String getpCancelYn() {
		return pCancelYn;
	}

	public void setpCancelYn(String pCancelYn) {
		this.pCancelYn = pCancelYn;
	}

	public String getpDesc1() {
		return pDesc1;
	}

	public void setpDesc1(String pDesc1) {
		this.pDesc1 = pDesc1;
	}

	public String getpDesc2() {
		return pDesc2;
	}

	public void setpDesc2(String pDesc2) {
		this.pDesc2 = pDesc2;
	}

	public String getpCreateDt() {
		return pCreateDt;
	}

	public void setpCreateDt(String pCreateDt) {
		this.pCreateDt = pCreateDt;
	}

	public String getpUpdateDt() {
		return pUpdateDt;
	}

	public void setpUpdateDt(String pUpdateDt) {
		this.pUpdateDt = pUpdateDt;
	}
	
}
