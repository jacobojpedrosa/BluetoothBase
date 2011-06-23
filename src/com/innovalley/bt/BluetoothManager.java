package com.innovalley.bt;

import android.os.SystemClock;



public class BluetoothManager extends Thread{
	private Bluetooth btDevice = null;
	private static final int timeBlock = 500;

	public BluetoothManager() throws Exception {
		this.btDevice = new Bluetooth();
	}

	public boolean isBtEnabled() {
		if (this.btDevice.isEnabled())return true;
		return false;
	}

	public void connect() throws Exception {
		this.btDevice.init("00:13:43:02:3E:FC");
		this.btDevice.connect();
		this.btDevice.sendMessage("CMD=1\n\r");
		SystemClock.sleep(500);
		//TTT
		this.run();
	}

	public boolean getStatus() {
		return true;
	}

	public void turnLeft() throws Exception {
		// TODO Auto-generated method stub
		this.btDevice.sendMessage("CMD=0,002,050,01,100\\r");
		SystemClock.sleep(timeBlock);
	}

	public void reset() throws Exception {
		this.btDevice.sendMessage("CMD=2\n\r");
		this.btDevice.disconnect();
		this.btDevice.connect();
		this.btDevice.sendMessage("CMD=1\n\r");
	}

	public void pairDevice(int pairShoe) {
		this.btDevice.pair();
	}

	public void disconnect() throws Exception {
		this.btDevice.sendMessage("CMD=2\n\r");
		this.btDevice.disconnect();
	}
	
}
