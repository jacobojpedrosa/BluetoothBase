package com.innovalley.bt;

import android.app.Application;
import android.content.SharedPreferences;

public class BluetoothManager {
	private BluetoothDevice[] devices = new BluetoothDevice[5];
	private Application application = null;
	private SharedPreferences preferences = null; 
	
	public BluetoothManager(Application application){
		this.application = application;
		this.preferences = this.application.getSharedPreferences("preferences", 0);
		
		devices[0].init(this.preferences.getString("device_0_address", "00:00:00:00:00:00"));
	}
}
