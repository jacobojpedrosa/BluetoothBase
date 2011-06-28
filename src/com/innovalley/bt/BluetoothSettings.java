package com.innovalley.bt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.innovalley.bluetooth.R;

public class BluetoothSettings extends Activity {
	//private int maxDevices = 5;
	private SharedPreferences settings;
	private ListView viewListBtDevices;
	private String[] DEVICES;
	private BluetoothManager btManager;
	
	// Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_devices_list);
		this.settings = PreferenceManager.getDefaultSharedPreferences(this);
		
		this.loadDevices();
		this.viewListBtDevices = (ListView)findViewById(R.id.device);
		this.viewListBtDevices.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1 , DEVICES));
		
		btManager = new BluetoothManager(getApplication());
		
		this.viewListBtDevices.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	//DEVICES[position]
		    	 Intent serverIntent = new Intent(getApplicationContext(), BluetoothPairing.class);
		         //startActivity(serverIntent);
		    	 startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
		    }
		  });
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bluetooth_devices_menu, menu);
        return true;
    }
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.goDevices:
        	Intent btDevices = new Intent(getApplicationContext(), BluetoothDevicesSettings.class);
			startActivity(btDevices);
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	private void loadDevices(){
		//String[] devices = new String[this.maxDevices];
		this.DEVICES = new String[10];
		for(int i=0; i<this.DEVICES.length; i++){
			if(settings.getString("device_"+i+"_name", "Device"+i)!=null){
				this.DEVICES[i]=settings.getString("device_"+i+"_name", "Device"+i);
			}else{
			}
		}
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
		switch (requestCode) {
        case REQUEST_CONNECT_DEVICE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                // Get the device MAC address
                String address = data.getExtras().getString(BluetoothPairing.EXTRA_DEVICE_ADDRESS);
                // Get the BLuetoothDevice object
                //BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                this.btManager.pairDevice(address);
                // Attempt to connect to the device
      
            }
            break;
        case REQUEST_ENABLE_BT:
        	Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
        }
    }
}
