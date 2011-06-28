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

import com.innovalley.bluetooth.R;

public class BluetoothSettings extends Activity {
	//private int maxDevices = 5;
	private SharedPreferences settings;
	private ListView viewListBtDevices;
	private String[] DEVICES;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_devices_list);
		this.settings = PreferenceManager.getDefaultSharedPreferences(this);
		
		this.loadDevices();
		this.viewListBtDevices = (ListView)findViewById(R.id.device);
		this.viewListBtDevices.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1 , DEVICES));
		
		this.viewListBtDevices.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	//DEVICES[position]
		    	
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
}
