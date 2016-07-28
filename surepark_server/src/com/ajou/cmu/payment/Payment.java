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
		
		payment = 1;
		
		long time = Long.parseLong(exitTime) - Long.parseLong(entryTime);
		System.out.println(time);	
		payment *= time;
	}

	public static boolean doPayment(String getpPayment, Object object) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//payment = 
		
		return true;
	}

}
