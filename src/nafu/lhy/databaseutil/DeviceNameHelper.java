package nafu.lhy.databaseutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DeviceNameHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "BluetoothDevice";
	public static final String ATTRIBUTE_DEVICE_NAME = "DeviceName";
	public static final String ATTRIBUTE_MAC_ADDRESS = "MacAddress";

	public DeviceNameHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create talbe " + TABLE_NAME + "(id INTEGER　PRIMARY KEY AUTOINCREMENT, " + ATTRIBUTE_DEVICE_NAME
				+ " VARCHAR, " + ATTRIBUTE_MAC_ADDRESS + " VARCHAR)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
