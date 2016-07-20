package com.ajou.cmu.sensor;

public class SensorStatus {
	private static int entryGate = 1;
	private static int exitGate = 1;

	private static int spot1 = 1;
	private static int spot2 = 1;
	private static int spot3 = 1;
	private static int spot4 = 1;
	
	private static boolean entryGateChanged = false;
	private static boolean exitGateChanged = false;

	private static boolean spot1Changed = false;
	private static boolean spot2Changed = false;
	private static boolean spot3Changed = false;
	private static boolean spot4Changed = false;
	
	public static boolean isEntryGateChanged() {
		return entryGateChanged;
	}
	public static void setEntryGateChanged(boolean entryGateChanged) {
		SensorStatus.entryGateChanged = entryGateChanged;
	}
	public static boolean isExitGateChanged() {
		return exitGateChanged;
	}
	public static void setExitGateChanged(boolean exitGateChanged) {
		SensorStatus.exitGateChanged = exitGateChanged;
	}
	public static boolean isSpot1Changed() {
		return spot1Changed;
	}
	public static void setSpot1Changed(boolean spot1Changed) {
		SensorStatus.spot1Changed = spot1Changed;
	}
	public static boolean isSpot2Changed() {
		return spot2Changed;
	}
	public static void setSpot2Changed(boolean spot2Changed) {
		SensorStatus.spot2Changed = spot2Changed;
	}
	public static boolean isSpot3Changed() {
		return spot3Changed;
	}
	public static void setSpot3Changed(boolean spot3Changed) {
		SensorStatus.spot3Changed = spot3Changed;
	}
	public static boolean isSpot4Changed() {
		return spot4Changed;
	}
	public static void setSpot4Changed(boolean spot4Changed) {
		SensorStatus.spot4Changed = spot4Changed;
	}

	private static String prevSensorValue = "111111";
	
	
	public static int getEntryGate() {
		return entryGate;
	}
	public static void setEntryGate(int entryGate) {
		SensorStatus.entryGate = entryGate;
	}
	public static int getExitGate() {
		return exitGate;
	}
	public static void setExitGate(int exitGate) {
		SensorStatus.exitGate = exitGate;
	}
	public static int getSpot1() {
		return spot1;
	}
	public static void setSpot1(int spot1) {
		SensorStatus.spot1 = spot1;
	}
	public static int getSpot2() {
		return spot2;
	}
	public static void setSpot2(int spot2) {
		SensorStatus.spot2 = spot2;
	}
	public static int getSpot3() {
		return spot3;
	}
	public static void setSpot3(int spot3) {
		SensorStatus.spot3 = spot3;
	}
	public static int getSpot4() {
		return spot4;
	}
	public static void setSpot4(int spot4) {
		SensorStatus.spot4 = spot4;
	}
	
	public static void setSensors(String sensors){
		if(sensors.charAt(0) != prevSensorValue.charAt(0)){
			entryGateChanged = true;
		}
		
		if(sensors.charAt(1) != prevSensorValue.charAt(1)){
			exitGateChanged = true;
		}
		
		if(sensors.charAt(2) != prevSensorValue.charAt(2)){
			spot1Changed = true;
		}
		
		if(sensors.charAt(3) != prevSensorValue.charAt(3)){
			spot2Changed = true;
		}
		
		if(sensors.charAt(4) != prevSensorValue.charAt(4)){
			spot3Changed = true;
		}
		
		if(sensors.charAt(5) != prevSensorValue.charAt(5)){
			spot4Changed = true;
		}
		
		
		
		entryGate =  Character.getNumericValue(sensors.charAt(0));
		exitGate = Character.getNumericValue(sensors.charAt(1));

		spot1 = Character.getNumericValue(sensors.charAt(2));
		spot2 = Character.getNumericValue(sensors.charAt(3));
		spot3 = Character.getNumericValue(sensors.charAt(4));
		spot4 = Character.getNumericValue(sensors.charAt(5));
		
		SensorStatus.prevSensorValue = sensors;
	}
	
	public static int getChangedSpotSensor(){
		if(spot1Changed){
			spot1Changed = false;
			return 1;
		}
		
		if(spot2Changed){
			spot2Changed = false;
			return 2;
		}
		
		if(spot3Changed){
			spot3Changed = false;
			return 3;
		}
		
		if(spot4Changed){
			spot4Changed = false;
			return 4;
		}
		
		return 0;
	}

}
