package cn.epicfx.winfxk.byenchant;

import cn.epicfx.winfxk.byenchant.main.Main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author Winfxk
 */
public class MyHander extends Handler {
	private MainActivity activity;
	private RelativeLayout.LayoutParams lp;

	public MyHander(MainActivity activity) {
		this.activity = activity;
		lp = (LayoutParams) activity.me.getLayoutParams();
	}

	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			activity.girl.setAlpha(msg.getData().getFloat("Alpha"));
			break;
		case 1:
			lp.setMargins(lp.leftMargin, msg.getData().getInt("Top"), lp.rightMargin, lp.bottomMargin);
			activity.me.setLayoutParams(lp);
			break;
		case 2:
			Intent intent = new Intent(activity, Main.class);
			activity.startActivity(intent);
			activity.finish();
			break;
		}
	}
}
