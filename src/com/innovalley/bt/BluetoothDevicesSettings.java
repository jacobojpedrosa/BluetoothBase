package com.innovalley.bt;


import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.innovalley.bluetooth.R;

public class BluetoothDevicesSettings extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.bluetooth_devices_settings);
	}
	
	
}
