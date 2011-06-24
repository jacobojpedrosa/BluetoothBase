package com.innovalley.bt;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.innovalley.bluetooth.R;


public class BluetoothMain extends Activity {
	private Button onOff;
	private Button send;

	// private String deviceMac ="00:13:43:02:64:83";//GPShoe Izquierdo
	private String deviceMac = "00:13:43:02:64:83";
	private BluetoothManager btManager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo1);

		this.send = (Button) findViewById(R.id.send);
		this.onOff = (Button) findViewById(R.id.onOff);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		this.onOff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					btManager = new BluetoothManager(deviceMac);
					btManager.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		this.send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					btManager.turnLeft();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.bluetooth_menu, menu);
		return true;
	}

}