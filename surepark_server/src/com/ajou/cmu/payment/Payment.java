package com.ajou.cmu.payment;

public class Payment {
	private static int payment = 0;
	private static int [] fee;

	public Payment(int fee[]){
		this.fee = fee;
	}
	
	public static int getPayment(){
		return payment;
	}
	
	public static void setPayment(String entryTime, String exitTime){
		/*요금 계산 */
		payment = 10;
	}

	public static boolean doPayment(String getpPayment, Object object) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
