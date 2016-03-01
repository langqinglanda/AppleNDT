package nafu.lhy.applendt;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import nafu.lhy.receiver.DiscoveryReceiver;

public class MainActivity extends Activity implements android.view.View.OnClickListener {

	final private static int CANCEL_FULL_SCREEN = 0;
	final private static int HIDE_SPLASH = 1;

	private ImageView iv_splash, iv_appleIcon;

	private Button bt_searchBlooth, bt_receiveData, bt_classResult;

	WindowManager.LayoutParams attrs;

	// Receive message and deal with state of full screen and visibility of
	// splash image;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case CANCEL_FULL_SCREEN:
				attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
				MainActivity.this.getWindow().setAttributes(attrs);
				break;
			case HIDE_SPLASH:
				iv_splash.setVisibility(View.INVISIBLE);
				break;
			}
			super.handleMessage(msg);
		}

	};

	// switching interface on time.

	TimerTask cancelFullScreen = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = new Message();
			msg.what = CANCEL_FULL_SCREEN;
			handler.sendMessage(msg);
		}
	};
	TimerTask hideSplash = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = new Message();
			msg.what = HIDE_SPLASH;
			handler.sendMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		attrs = getWindow().getAttributes();
		initView();
		// Timer of canceling full screen;
		Timer cancelFullScreenTimer = new Timer();
		cancelFullScreenTimer.schedule(cancelFullScreen, 3000);
		// Timer of hiding splash image;
		Timer hideSplashTimer = new Timer();
		hideSplashTimer.schedule(hideSplash, 4000);
	}

	// initializing widgets and relating widgets and events
	private void initView() {
		// TODO Auto-generated method stub
		float height = getResources().getDisplayMetrics().heightPixels;
		iv_splash = (ImageView) findViewById(R.id.iv_splash);
		iv_appleIcon = (ImageView) findViewById(R.id.iv_appleIcon);
		iv_appleIcon.setMaxHeight((int) (height / 6));
		iv_appleIcon.setPadding(0, (int) (height / 15), 0, 0);
		bt_receiveData = (Button) findViewById(R.id.bt_receiveData);
		bt_receiveData.setOnClickListener(this);
		bt_classResult = (Button) findViewById(R.id.bt_classResult);
		bt_classResult.setOnClickListener(this);
		bt_searchBlooth = (Button) findViewById(R.id.bt_searchBluetooth);
		bt_searchBlooth.setOnClickListener(this);
		bt_receiveData.setHeight((int) (height / 6));
		bt_classResult.setHeight((int) (height / 6));
		bt_searchBlooth.setHeight((int) (height / 6));
		bt_receiveData.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 27);
		bt_classResult.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 27);
		bt_searchBlooth.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 27);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// handling clicked events of widgets
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.bt_searchBluetooth:
			intent = new Intent(MainActivity.this, SearchBluetoothActivity.class);
			startActivity(intent);
			break;
		case R.id.bt_receiveData:
			intent = new Intent(MainActivity.this, ReceiveDataActivity.class);
			startActivity(intent);
			break;
		case R.id.bt_classResult:
			intent = new Intent(MainActivity.this, ClassResultActivity.class);
			startActivity(intent);
			break;
		}

	}

}
