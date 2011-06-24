package com.innovalley.bt;
import android.os.SystemClock;

public class BluetoothManager extends Thread {

	private Bluetooth left = null;
	private static final int timeBlock = 500;

	public BluetoothManager(String mac) throws Exception {
		this.left = new Bluetooth(mac);
	}

	public boolean isBtEnabled() {
		if (this.left.isEnabled())return true;
		return false;
	}

	public void run() {
		connect();
	}

	public boolean getStatus() {
		return true;
	}

	public void turnLeft() throws Exception {
		this.left.sendMessage("CMD=0,002,050,01,100\\r");
		SystemClock.sleep(timeBlock);
	}

	public void reset() throws Exception {
		disconnect();
		this.left.connect();
		this.left.sendMessage("CMD=1\n\r");
		SystemClock.sleep(timeBlock);
	}

	public void pairDevice(int pairShoe) {
		this.left.pair();
	}

	public void disconnect() throws Exception {
		this.left.sendMessage("CMD=2\n\r");
		this.left.disconnect();
	}
	
	private void connect() {
		try {
			this.left.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.left.sendMessage("CMD=1\n\r");
			SystemClock.sleep(timeBlock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
