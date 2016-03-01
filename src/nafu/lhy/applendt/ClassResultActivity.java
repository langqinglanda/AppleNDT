package nafu.lhy.applendt;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import nafu.lhy.utils.InitViewsUtils;

public class ClassResultActivity extends Activity implements OnClickListener {

	private ImageView iv_back_classResult, iv_appleIcon_classResult;
	private LinearLayout ll_classResult, ll_classOne_description, ll_classOne_one;
	private TextView tv_classOne, tv_classOne_description, tv_classOne_one;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_result);
		InitView();
	}

	private void InitView() {
		// TODO Auto-generated method stub
		float height = getResources().getDisplayMetrics().heightPixels;
		int textSize = (int) height / 60;

		// initializing widgets with reflection
		InitViewsUtils.InitViews(this);
		iv_back_classResult.setOnClickListener(this);
		tv_classOne_description.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_back_classResult:
			finish();
			break;
		}
	}

}
