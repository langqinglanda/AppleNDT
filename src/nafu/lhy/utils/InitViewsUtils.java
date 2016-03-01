package nafu.lhy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;
import nafu.lhy.applendt.R;
import nafu.lhy.applendt.R.id;

/**
 * @version 1.0
 * @author xyw
 *
 */
public class InitViewsUtils {

	// initializing the widgets in the activity
	public static void InitViews(Activity activity) {
		Class<? extends Activity> cls = activity.getClass();
		Class<id> idCls = R.id.class;
		Field[] clsFields = cls.getDeclaredFields();
		for (int i = 0; i < clsFields.length; i++) {
			try {
				Class<?> type = clsFields[i].getType();
				if (View.class.isAssignableFrom(type)) {
					clsFields[i].setAccessible(true);
					// Reflection
					Method method = cls.getMethod("findViewById", new Class[] { int.class });
					Field idField = idCls.getDeclaredField(clsFields[i].getName());
					Object args = idField.get(R.id.class.newInstance());
					Object value = method.invoke(activity, new Object[] { args });
					clsFields[i].set(activity, value);

				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
