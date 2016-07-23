package com.ajou.cmu.sensor;

public class SensorStatus {
	
	private static int[] spots;
	private static int[] entryGates;
	private static int[] exitGates;
	
	private static boolean[] entryGateChanged;
	private static boolean[] exitGateChanged;
	private static boolean[] spotChanged;
	
	private static String prevSensorValue = null;
	private static boolean initState = false;
	
	public static boolean getInitState(){
		return initState;
	}
	
	public static void init(int spotNum, int entryGateNum, int exitGateNum){
		spots = new int[spotNum];
		entryGates = new int[entryGateNum];
		exitGates = new int[exitGateNum];
		
		spotChanged = new boolean[spotNum];
		entryGateChanged = new boolean[entryGateNum];
		exitGateChanged = new boolean[exitGateNum];
		initState = true;
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<spotNum + entryGateNum + exitGateNum; i++){
			sb.append("1");
		}
		prevSensorValue = sb.toString();
	}
	
			
	public static void setSensors(String sensors){
		System.out.println("sensor=" + sensors);
		System.out.println("####entryGates.length#####=" + entryGates.length);
		System.out.println("####exitGates#####=" + exitGates.length);
		System.out.println("####sensors.length()#####=" + sensors.length());
		
		System.out.println("prev=" + prevSensorValue);
		System.out.println("curr=" + sensors);
		for(int i=0; i<sensors.length(); i++){
			//System.out.println("##########=" + i);
			if(sensors.charAt(i) != prevSensorValue.charAt(i)){
				for(int j=0; j<entryGates.length; j++)
					entryGateChanged[j] = false;

				for(int j=0; j<exitGates.length; j++)
					exitGateChanged[j] = false;
				
				for(int j=0; j<spots.length; j++)
					spotChanged[j] = false;
				
				if(i < entryGates.length){
					entryGateChanged[i] = true;
				}else if(i >= entryGates.length && i < entryGates.length + exitGates.length){
					exitGateChanged[i-entryGates.length] = true;
				}else{
					spotChanged[i - entryGates.length - exitGates.length] = true;
					System.out.println("spot changed = " + (i - entryGates.length - exitGates.length));
				}
				
			}
		}
				
		
		/*
		entryGate =  Character.getNumericValue(sensors.charAt(0));
		exitGate = Character.getNumericValue(sensors.charAt(1));

		spot1 = Character.getNumericValue(sensors.charAt(2));
		spot2 = Character.getNumericValue(sensors.charAt(3));
		spot3 = Character.getNumericValue(sensors.charAt(4));
		spot4 = Character.getNumericValue(sensors.charAt(5));
		*/
		/*
		entryGate = sensors.charAt(0) - 48;
		exitGate = sensors.charAt(1)  - 48;

		spot1 = sensors.charAt(2) - 48;
		spot2 = sensors.charAt(3) - 48;
		spot3 = sensors.charAt(4) - 48;
		spot4 = sensors.charAt(5) - 48;
		*/
		int eIndex = 0;
		int xIndex = 0;
		int sIndex = 0;
		for(int i=0; i<sensors.length(); i++){
			if(i < entryGates.length){
				SensorStatus.setEntryGate(sensors.charAt(i)-48,eIndex);
				eIndex++;
			}
			
			if(i >= entryGates.length && i < entryGates.length + exitGates.length){
				SensorStatus.setExitGate(sensors.charAt(i)-48,xIndex);
				System.out.println("###############################setexitgate");
				xIndex++;
			}
			
			if(i >= entryGates.length + exitGates.length){
				SensorStatus.setSpots(sensors.charAt(i)-48,sIndex);
				sIndex++;
			}
		}
		
		for(int i=0; i<spotChanged.length; i++)
			System.out.println("===="+spotChanged[i]);
		
		SensorStatus.prevSensorValue = sensors;
		System.out.println("===="+prevSensorValue);
	}
	
	
	
	public static void setSpots(int i, int sIndex) {
		// TODO Auto-generated method stub
		spots[sIndex] = i;
		
	}


	public static void setExitGate(int i, int xIndex) {
		// TODO Auto-generated method stub
		exitGates[xIndex] = i;
		
	}


	public static void setEntryGate(int i, int eIndex) {
		// TODO Auto-generated method stub
		entryGates[eIndex] = i;
	}


	public static int getChangedSpotSensor(){
		for(int i=0; i<spots.length; i++){
			if(spotChanged[i]){
				spotChanged[i] = false;
				return i+1;
			}
		}
		
		return 0;
	}


	public static int getEntryGate(int i) {
		// TODO Auto-generated method stub
		return entryGates[i-1];
	}


	public static int getExitGate(int i) {
		// TODO Auto-generated method stub
		return exitGates[i-1];
	}


	public static int getSpot(int i) {
		// TODO Auto-generated method stub
		return spots[i-1];
	}

}
