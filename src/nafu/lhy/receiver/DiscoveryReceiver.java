package nafu.lhy.receiver;

import java.util.List;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import nafu.lhy.applendt.SearchBluetoothActivity;

/**
 * @version 1.0
 * @author xyw
 *
 */

// handling the action of found devices
public class DiscoveryReceiver extends BroadcastReceiver {

	private Context context;
	private List<BluetoothDevice> deviceName;
	private Handler handler;

	public DiscoveryReceiver(Context context, List<BluetoothDevice> deviceName, Handler handler) {
		super();
		this.context = context;
		this.deviceName = deviceName;
		this.handler = handler;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
			BluetoothDevice bd = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			deviceName.add(bd);
			Message msg = new Message();
			msg.what = SearchBluetoothActivity.DEVICE_DISCOVERIED;
			handler.sendMessage(msg);
		} else if (intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
			Message msg = new Message();
			msg.what = SearchBluetoothActivity.FINISH_DISCOVERIED;
			handler.sendMessage(msg);
		}
	}

}
