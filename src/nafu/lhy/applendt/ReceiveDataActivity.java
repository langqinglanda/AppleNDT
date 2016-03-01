package nafu.lhy.applendt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ReceiveDataActivity extends Activity implements OnClickListener {

	private ImageView bt_back_ReceiveData, iv_appleIcon_receiveData;
	private TextView tv_resistance_value, tv_resistance_value_unit, tv_parallel_resistance_value,
			tv_parallel_resistance_value_unit, tv_series_resistance_value, tv_series_resistance_value_unit,
			tv_specific_resistance_value, tv_specific_resistance_value_unit;
	private LinearLayout ll_resistance, ll_parallel_resistance, ll_series_resistance, ll_specific_resistance;
	private Button bt_receiveData_receiveData;
	private LinearLayout.LayoutParams lp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receive_data);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		float height = getResources().getDisplayMetrics().heightPixels;
		float width = getResources().getDisplayMetrics().widthPixels;
		int paddingValue = (int) (height / 50);
		float textSize = height / 70;

		tv_resistance_value = (TextView) findViewById(R.id.tv_resistance_value);
		tv_resistance_value_unit = (TextView) findViewById(R.id.tv_resistance_value_unit);
		tv_specific_resistance_value = (TextView) findViewById(R.id.tv_specific_resistance_value);
		tv_specific_resistance_value_unit = (TextView) findViewById(R.id.tv_specific_resistance_value_unit);
		tv_series_resistance_value = (TextView) findViewById(R.id.tv_series_resistance_value);
		tv_series_resistance_value_unit = (TextView) findViewById(R.id.tv_series_resistance_value_unit);
		tv_parallel_resistance_value = (TextView) findViewById(R.id.tv_parallel_resistance_value);
		tv_parallel_resistance_value_unit = (TextView) findViewById(R.id.tv_parallel_resistance_value_unit);
		ll_resistance = (LinearLayout) findViewById(R.id.ll_resistance);
		ll_specific_resistance = (LinearLayout) findViewById(R.id.ll_specific_resistance);
		ll_parallel_resistance = (LinearLayout) findViewById(R.id.ll_parallel_resistance);
		ll_series_resistance = (LinearLayout) findViewById(R.id.ll_series_resistance);
		bt_back_ReceiveData = (ImageView) findViewById(R.id.iv_back_ReceiveData);
		bt_receiveData_receiveData = (Button) findViewById(R.id.bt_recevieData_receiveData);
		iv_appleIcon_receiveData = (ImageView) findViewById(R.id.iv_appleIcon_receiveData);
		lp = (LayoutParams) ll_resistance.getLayoutParams();
		tv_resistance_value.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_resistance_value.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
		tv_resistance_value_unit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_specific_resistance_value.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_specific_resistance_value.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
		tv_specific_resistance_value_unit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_parallel_resistance_value.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_parallel_resistance_value.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
		tv_parallel_resistance_value_unit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_series_resistance_value.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_series_resistance_value.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
		tv_series_resistance_value_unit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		tv_series_resistance_value_unit.setWidth(40);
		bt_back_ReceiveData.setOnClickListener(this);
		bt_receiveData_receiveData.setHeight((int) height / 6);
		iv_appleIcon_receiveData.setMinimumHeight((int) height / 7);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.iv_back_ReceiveData:
			finish();
			break;
		}
	}

}
