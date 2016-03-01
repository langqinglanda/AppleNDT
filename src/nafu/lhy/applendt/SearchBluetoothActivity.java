package nafu.lhy.applendt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import nafu.lhy.adapter.DeviceAdapter;
import nafu.lhy.databaseutil.DeviceNameHelper;
import nafu.lhy.receiver.DiscoveryReceiver;

public class SearchBluetoothActivity extends Activity implements OnClickListener {

	private ListView lv_bluetoothDevice;
	private ImageView iv_appleIcon_searchBluetooth, iv_sliding_bluetoothSearch, iv_back_bluetoothSearch;
	private Button bt_searchBluetooth_searchBluetooth;
	private LinearLayout ll_blueDevice;
	final private static int LAYOUTHEIGHT = 1;
	final public static int FINISH_DISCOVERIED = 2;
	final public static int REFRESH_LISTVIEW = 3;
	final public static int DEVICE_DISCOVERIED = 4;

	private DeviceAdapter bdApdater;
	private DeviceNameHelper bdHelper;
	private List<BluetoothDevice> devices;
	private BluetoothAdapter ba;

	private IntentFilter foundFilter, finishFilter;
	private DiscoveryReceiver mReceiver;

	// receive message and update the content of widgets.
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case SearchBluetoothActivity.DEVICE_DISCOVERIED:
				bdApdater.notifyDataSetChanged();
				Toast.makeText(SearchBluetoothActivity.this, "发现新设备:" + devices.get(devices.size() - 1).getName(),
						Toast.LENGTH_LONG).show();
				break;
			case SearchBluetoothActivity.FINISH_DISCOVERIED:
				bt_searchBluetooth_searchBluetooth.setText(getResources().getString(R.string.bt_search_bluetooth_text));
				break;
			case SearchBluetoothActivity.REFRESH_LISTVIEW:

				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_bluetooth);
		initView();
	}

	// initializing widget and relating widgets and events.
	// according the size of phone screen to adjust size of widgets and text in
	// them.
	private void initView() {
		// TODO Auto-generated method stub

		int height = getResources().getDisplayMetrics().heightPixels;
		lv_bluetoothDevice = (ListView) findViewById(R.id.lv_bluetoothDevice);
		iv_sliding_bluetoothSearch = (ImageView) findViewById(R.id.iv_sliding_searchBluetooth);
		iv_appleIcon_searchBluetooth = (ImageView) findViewById(R.id.iv_appleIcon_searchBluetooth);
		devices = new ArrayList<BluetoothDevice>();
		ba = BluetoothAdapter.getDefaultAdapter();
		if (ba != null) {
			if (!ba.isEnabled()) {

				ba.enable();
				Toast.makeText(SearchBluetoothActivity.this, "蓝牙设备开启中。。。", Toast.LENGTH_LONG).show();

			}
			Set<BluetoothDevice> tmpDevices = ba.getBondedDevices();
			Iterator<BluetoothDevice> it = tmpDevices.iterator();
			if (tmpDevices != null) {
				while (it.hasNext()) {
					devices.add((BluetoothDevice) it.next());
				}
				bdApdater = new DeviceAdapter(SearchBluetoothActivity.this, devices);
				lv_bluetoothDevice.setAdapter(bdApdater);
			}
		} else {
			Toast.makeText(SearchBluetoothActivity.this, "本机没有蓝牙设备！", Toast.LENGTH_LONG).show();
			bt_searchBluetooth_searchBluetooth.setEnabled(false);
		}
		iv_appleIcon_searchBluetooth.setMaxHeight(height / 6);
		iv_appleIcon_searchBluetooth.setPadding(0, height / 12, 0, 0);
		ll_blueDevice = (LinearLayout) findViewById(R.id.ll_blueDevice);
		ll_blueDevice.setPadding(0, height / 12, 0, 0);
		bt_searchBluetooth_searchBluetooth = (Button) findViewById(R.id.bt_searchBluetooth_searchBluetooth);
		bt_searchBluetooth_searchBluetooth.setHeight(height / 6);
		bt_searchBluetooth_searchBluetooth.setTextSize(height / 50);
		iv_back_bluetoothSearch = (ImageView) findViewById(R.id.iv_back_BluetoothSearch);
		iv_back_bluetoothSearch.setOnClickListener(this);
		bt_searchBluetooth_searchBluetooth.setOnClickListener(this);
		mReceiver = new DiscoveryReceiver(SearchBluetoothActivity.this, devices, handler);
		foundFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mReceiver, foundFilter);
		finishFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(mReceiver, finishFilter);
		lv_bluetoothDevice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (ba.isDiscovering()) {
					ba.cancelDiscovery();
				}

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	// handling the clicked events of widgets.
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.iv_back_BluetoothSearch:
			finish();
			break;
		case R.id.bt_searchBluetooth_searchBluetooth:
			bt_searchBluetooth_searchBluetooth.setText("搜索中...");
			Toast.makeText(SearchBluetoothActivity.this, "开始搜索蓝牙设备", Toast.LENGTH_LONG).show();
			if (devices.size() == 0) {
				Set<BluetoothDevice> tmpDevices = ba.getBondedDevices();
				Iterator<BluetoothDevice> it = tmpDevices.iterator();
				if (tmpDevices != null) {
					while (it.hasNext()) {
						devices.add((BluetoothDevice) it.next());
					}
					bdApdater = new DeviceAdapter(SearchBluetoothActivity.this, devices);
					lv_bluetoothDevice.setAdapter(bdApdater);
				}
			}
			if (ba.isDiscovering()) {
				ba.cancelDiscovery();
			}
			ba.startDiscovery();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

}
