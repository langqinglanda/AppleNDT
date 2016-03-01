package nafu.lhy.databaseutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DeviceDBManager {

	private DeviceNameHelper dnHelper;
	private SQLiteDatabase db;

	public DeviceDBManager(Context context) {
		dnHelper = new DeviceNameHelper(context, null, null, 1);
		db = dnHelper.getWritableDatabase();
	}

	public long insertDevice(ContentValues cv) {
		long result=db.insert(DeviceNameHelper.TABLE_NAME, null, cv);
		return result;
	}

}
