package nafu.lhy.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import nafu.lhy.applendt.R;

/**
 * @version 1.0
 * @author xyw Adapter for the discoveried or paired devices to show in the
 *         view.
 */

public class DeviceAdapter extends BaseAdapter {

	private Context context;

	private List<BluetoothDevice> devices;

	public DeviceAdapter(Context context, List<BluetoothDevice> devices) {
		this.context = context;
		this.devices = devices;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return devices.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return devices.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// initializing the view of items in listview.
		ViewHolder viewHolder = null;
		float height = context.getResources().getDisplayMetrics().heightPixels;
		int textSize = (int) height / 70;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item_bluetooth_device, null, false);
			viewHolder.deviceNameView = (TextView) convertView.findViewById(R.id.tv_deviceName);
			viewHolder.selectedView = (TextView) convertView.findViewById(R.id.tv_selected);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		BluetoothDevice bd = devices.get(position);
		viewHolder.deviceNameView.setText(bd.getName());
		viewHolder.deviceNameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		// whether a device is paired.
		if (bd.getBondState() == BluetoothDevice.BOND_BONDED) {

			viewHolder.selectedView.setBackgroundResource(R.drawable.bound_no_connected);
		}
		return convertView;
	}

	public List<BluetoothDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<BluetoothDevice> devices) {
		this.devices = devices;
	}

	private final class ViewHolder {
		TextView deviceNameView;
		TextView selectedView;
	}

}
