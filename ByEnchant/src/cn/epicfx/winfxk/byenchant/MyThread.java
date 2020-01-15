package cn.epicfx.winfxk.byenchant;

import android.os.Bundle;
import android.os.Message;

/**
 * @author Winfxk
 */
public class MyThread extends Thread {
	private MainActivity activity;
	private int Key;

	public MyThread(MainActivity activity, int i) {
		Key = i;
		this.activity = activity;
	}

	@Override
	public void run() {
		try {
			sleep(1000);
			switch (Key) {
			case 0:
				Bundle bundle;
				Message msg;
				for (float alpha = 0; alpha < 1; alpha += 0.00392156862745) {
					msg = new Message();
					msg.what = 0;
					bundle = new Bundle();
					bundle.putFloat("Alpha", alpha);
					msg.setData(bundle);
					activity.Handler.sendMessage(msg);
					sleep(3);
				}
				break;
			case 1:
				int is = activity.me.getHeight();
				int y = (int) (is * 2.5), x = is * -1;
				for (double s = 1; s < 7; s++) {
					for (int top = x; top < y; top += 2) {
						msg = new Message();
						msg.what = 1;
						bundle = new Bundle();
						bundle.putInt("Top", top);
						msg.setData(bundle);
						activity.Handler.sendMessage(msg);
						sleep(1);
						x = top;
					}
					sleep(10);
					for (int top = x; top > y - (is / (s / 3)); top -= 2) {
						msg = new Message();
						msg.what = 1;
						bundle = new Bundle();
						bundle.putInt("Top", top);
						msg.setData(bundle);
						activity.Handler.sendMessage(msg);
						sleep(1);
						x = top;
					}
					sleep(10);
				}
				for (int top = x; top < y; top++) {
					msg = new Message();
					msg.what = 1;
					bundle = new Bundle();
					bundle.putInt("Top", top);
					msg.setData(bundle);
					activity.Handler.sendMessage(msg);
					sleep(1);
					x = top;
				}
				sleep(500);
				activity.Handler.sendEmptyMessage(2);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
