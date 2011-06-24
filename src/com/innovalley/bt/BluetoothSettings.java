package com.innovalley.bt;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.innovalley.bluetooth.R;

public class BluetoothSettings extends Activity {
	private TextView text1;
	private int maxDevices = 5;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_settings);
        
		this.text1=(TextView)findViewById(R.id.textView1);
		
		// Restore preferences
	    SharedPreferences settings = getSharedPreferences("bluetooth_devices_settings", 0);
	    /*String devices = settings.
	    String dev0 = settings.getString("device_0_name", "00:00:00:00:00:00");
	       
		this.text1.setText(devices);
		*/
		String devs= "";
		for(int i=0; i<=this.maxDevices; i++){
			if(settings.contains("device_3_address")){
				//devs=devs+settings.getString("device_"+i+"_name", "00:00:00:00:00:00");
				devs="si_device";
			}else{
				devs=devs+"no_device_"+i+"_name";
			}
		}
		
		this.text1.setText(devs);
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
}
