package com.innovalley.bt;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.app.AlertDialog;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;

public class Bluetooth {
	
	// Local device Bluetooth adapter
	private final BluetoothAdapter mBluetoothAdapter;
	
	// Remote device
	private BluetoothDevice remoteDevice;
	
	// Bluetooth socket - RFCOMM
	private BluetoothSocket btSocket;
	
	// A means of writing data to a target in a byte-wise manner.
	private OutputStream outStream;
	
	//Application 
	private Application application;
	
    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_CONNECTING = 1; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 2;  // now connected to a remote device
	
	private boolean connected;

	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	public Bluetooth(Application application){
		this.application=application;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // Local
		
	}
	
	public Bluetooth(String macAddress, Application application) {
		this(application);
		remoteDevice = mBluetoothAdapter.getRemoteDevice(macAddress); // Remote
		try {
			if (remoteDevice == null)
				throw new Exception("It couldn't be connected with the device "	+ macAddress);
			btSocket = remoteDevice.createRfcommSocketToServiceRecord(MY_UUID);
			mBluetoothAdapter.cancelDiscovery();
		} catch (Exception e) {
		}
		connected = false;
	}

	public void connect() throws Exception {
		try {
			btSocket.connect();
			connected = true;
		} catch (Exception e) {
			try {
				btSocket.close();
			} catch (Exception e2) {
				throw new Exception("Unable to close socket during connection failure" + e2.getMessage());
			}
		}

		try {
			outStream = btSocket.getOutputStream();
		} catch (IOException e) {
			throw new Exception("Output stream creation failed." + e.getMessage());
		}
	}

	public void sendMessage(String message) throws Exception {
		try {
			byte[] msgBuffer = message.getBytes();
			outStream.write(msgBuffer);
			outStream.flush();
		} catch (Exception e) {
			throw new Exception("BLUETOOTH SEND MESSAGE:Exception during write." + e.getMessage());
		}
	}

	public void disconnect() throws Exception {
		try {			
			//outStream.close();
			//outStream.flush();
			btSocket.close();			
			connected = false;
		} catch (Exception e2) {
			throw new Exception("Unable to close socket during connection failure" + e2.getMessage());
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean conn) {
		connected = conn;
	}

	public boolean isEnabled() {
		return mBluetoothAdapter.isEnabled();
	}
	
}
