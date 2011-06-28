package com.innovalley.bt;

import android.app.Application;
import android.os.SystemClock;

public class BluetoothManager extends Thread {

	private Bluetooth btDevice;
	private static final int timeBlock = 500;
	private Application application;
	
	public BluetoothManager(Application application) {
		this.application = application;
		btDevice = new Bluetooth(this.application);
	}
	
	public BluetoothManager(String mac, Application application) {
		this.application = application;
		btDevice = new Bluetooth(mac, this.application);
	}

	public boolean isBtEnabled() {
		if (btDevice.isEnabled())
			return true;
		return false;
	}

	public void run() {
		connect();
	}

	public boolean getStatus() {
		return true;
	}

	public void send(String message) throws Exception {
		this.btDevice.sendMessage(message);
		SystemClock.sleep(timeBlock);
	}

	public void reset() throws Exception {
		disconnect();
		this.btDevice.connect();
		this.btDevice.sendMessage("CMD=1\n\r");
		SystemClock.sleep(timeBlock);
	}

	public void pairDevice(int pairShoe) {
		// this.btDevice.pair();
	}

	public void disconnect() {
		try {
			btDevice.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connect() {
		try {
			this.btDevice.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.btDevice.sendMessage("CMD=1\n\r");
			SystemClock.sleep(timeBlock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
