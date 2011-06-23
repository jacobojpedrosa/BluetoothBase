package com.innovalley.bt;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class Bluetooth{
	private String address;
	private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private boolean connected;
     
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    
	public boolean isConnected() {
		return connected;
	}
	
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public Bluetooth(){
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		this.connected = false;
		//this.application = application;
	}

	public boolean isEnabled(){
		return this.mBluetoothAdapter.isEnabled();
	}
	
	public void init(String address){
		this.address = address;
		this.run();
	}
	
	public void run(){
		BluetoothDevice device =mBluetoothAdapter.getRemoteDevice(address);
        try{
		if(device == null){
        	throw new Exception("It couldn't be connected with the device"+this.address);
        }
        btSocket =device.createRfcommSocketToServiceRecord(MY_UUID);
        
        mBluetoothAdapter.cancelDiscovery();
        }catch(Exception e){
        	
        }
	}
	
	public void connect() throws Exception{
		try{
        	btSocket.connect();
        	this.connected=true;
        }
        catch(Exception e){
        	try{
        		btSocket.close();
        	}
        	catch(Exception e2){
        		throw new Exception("ON RESUME: Unable to close socket during connection failure"+e2.getMessage());
        	}
        }
        
        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
        	throw new Exception("BLUETOOTH CONSTRUCTOR:Output stream creation failed."+e.getMessage());
        }
	}
	
	
	public void sendMessage(String message) throws Exception{
		try{
			byte[] msgBuffer = message.getBytes();
			outStream.write(msgBuffer);
			outStream.flush();
		}catch(Exception e){
			throw new Exception("BLUETOOTH SEND MESSAGE:Exception during write."+e.getMessage());
		}
	}
	
	public void disconnect() throws Exception{
		try{
			this.btSocket.close();
			this.outStream.close();
			this.connected = false;
    	}
    	catch(Exception e2){
    		throw new Exception("ON RESUME: Unable to close socket during connection failure"+e2.getMessage());
    	}
	}



}
