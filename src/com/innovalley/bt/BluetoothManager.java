package com.innovalley.bt;
import android.os.SystemClock;

public class BluetoothManager extends Thread {

	private Bluetooth btDevice;
	private static final int timeBlock = 500;

	public BluetoothManager(String mac){
		btDevice = new Bluetooth(mac);
	}

	public boolean isBtEnabled() {
		if (btDevice.isEnabled()) return true;
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
		//this.btDevice.pair();
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
			//this.btDevice.connect();
			btDevice.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			this.btDevice.sendMessage("CMD=1\n\r");
//			SystemClock.sleep(timeBlock);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
//	public void pair() {
//		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//		AlertDialog.Builder ab = new AlertDialog.Builder(this.application.getApplicationContext());
//		ab.setTitle("Pairment");
//		String[] data = new String[pairedDevices.size()];
//		int i = 0;
//		// If there are paired devices
//		if (pairedDevices.size() > 0) {
//			// Loop through paired devices
//			for (BluetoothDevice device : pairedDevices) {
//				data[i] = device.getName() + "\n" + device.getAddress();
//				i++;
//			}
//		}
//
//		ab.setSingleChoiceItems(data, 0, new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//
//			}
//		}).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//			}
//		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//				// on cancel button action
//			}
//		});
//		ab.show();
//	}
	
}
