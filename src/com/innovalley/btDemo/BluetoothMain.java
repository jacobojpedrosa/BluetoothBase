package com.innovalley.btDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.innovalley.bluetooth.R;
import com.innovalley.bt.BluetoothManager;




public class BluetoothMain extends Activity {
	private Button onOff;
	private Button send;
	
	//private String deviceMac ="00:13:43:02:64:83";//GPShoe Izquierdo Azul=>00:13:43:02:64:83
	private String deviceMac ="00:13:43:02:3E:FC";//GPShoe Derecho Azul=>00:13:43:02:64:83
	private BluetoothManager btManager = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1);
        
        this.btManager = new BluetoothManager();
        this.send=(Button)findViewById(R.id.send);
        this.onOff=(Button)findViewById(R.id.onOff);
        
        
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		

		this.onOff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					//BluetoothManager.getInstance().init(deviceMac);
					btManager.init(deviceMac);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		this.send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					//BluetoothManager.getInstance().sendMessageTest();
					btManager.sendMessageTest();
				} catch (Exception e) {
				}
			}
		});
		
	}
    
    
    
}