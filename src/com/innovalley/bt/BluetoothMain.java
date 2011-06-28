package com.innovalley.bt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.innovalley.bluetooth.R;

public class BluetoothMain extends Activity {
	private Button onOff;
	private Button send;
	//private String deviceMac = "00:13:43:02:64:83";// GPShoe Izquierdo
	private String deviceMac ="00:13:43:02:3E:FC";//GPShoe Derecho
	// private String deviceMac = "78:1D:BA:13:9F:9F"; //Movil Comet
	// private String deviceMac = "00:09:dd:50:66:ee";//Laptop dongle
	private static final String TAG = "BluetoothMain";
	// private String startCmd = "CMD=1";
	// private String endCmd = "CMD=2";
	private String cmd = "CMD=0,002,050,01,100";
	private BluetoothManager btManager;
	private int mode = 0; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo1);
		send = (Button) findViewById(R.id.send);
		onOff = (Button) findViewById(R.id.onOff);
	}

	@Override
	protected void onResume() {
		super.onResume();
		onOff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				btManager = new BluetoothManager(deviceMac, getApplication());
				if (mode == 0) {					
					try {
						btManager.start();						
						SystemClock.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					mode = 1;
				} else {
					mode = 0;
					btManager.disconnect();
					Log.e(TAG, "close() of connect socket failed");
				}
			}
		});

		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					btManager.send(cmd);
					SystemClock.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.goSettings:
			Intent btSettings = new Intent(getApplicationContext(),
					BluetoothSettings.class);
			startActivity(btSettings);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.e(TAG, "-- ON STOP --");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		btManager.disconnect();
		Log.e(TAG, "--- ON DESTROY ---");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.bluetooth_menu, menu);
		return true;
	}

}