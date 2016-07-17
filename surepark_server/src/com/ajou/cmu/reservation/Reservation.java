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
	
	public Reservation setReservation(Map<String, Object> data) {
		
		if(data == null)
			return null;
		
		this.id = Integer.parseInt(String.valueOf(data.get("P_PK")));
		this.userID = Integer.parseInt(String.valueOf(data.get("U_PK")));
		this.category = Integer.parseInt(String.valueOf(data.get("CATEGORY")));
		this.name = String.valueOf(data.get("NAME"));
		this.price = String.valueOf(data.get("PRICE"));
		this.buyDate = String.valueOf(data.get("FIRST_BUY_DATE"));
		this.effectiveUserNumber = String.valueOf(data.get("EFFECTIVE_USE_NUMBER"));
		this.fullBox = Boolean.parseBoolean(String.valueOf(data.get("FULL_BOX")));
		this.certification = Boolean.parseBoolean(String.valueOf(data.get("CERTIFICATION_OX")));
		this.description = String.valueOf(data.get("DESCRIPTION"));
		this.imageInfo = Integer.parseInt(String.valueOf(data.get("IMAGE_INFO")));
		this.locationInfo = String.valueOf(data.get("LOCATION_INFO"));
		this.quantity = Integer.parseInt(String.valueOf(data.get("QUANTITY")));
		this.tradeType = Integer.parseInt(String.valueOf(data.get("TRADE_TYPE")));
		this.currentStatus = Integer.parseInt(String.valueOf(data.get("CURRENT_STATUS")));
		this.registrationTime = String.valueOf(data.get("REGISTRATION_TIME"));
		
		productMap = data;
		return this;
		
	}

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
