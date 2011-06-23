package com.innovalley.bt;



public class BluetoothManager extends Thread{
//	private static BluetoothManager INSTANCE = null;
	private Bluetooth btDevice = null;
	
/*	
	public static BluetoothManager getInstance() {
		if(INSTANCE == null) INSTANCE = new BluetoothManager();
		return INSTANCE;
	}
*/
	public BluetoothManager(){
		this.btDevice=new Bluetooth();
	}
	
	public void init(String mac){
		try{
			this.btDevice.init(mac);
			this.btDevice.connect();
			this.btDevice.sendMessage("CMD=1\n\r");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.run();
	}
	
	public void sendMessageTest(){
		try {
			this.btDevice.sendMessage("CMD=0,005,050,01,50\n\r");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
