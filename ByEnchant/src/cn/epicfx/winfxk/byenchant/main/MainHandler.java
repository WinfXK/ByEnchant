package cn.epicfx.winfxk.byenchant.main;

import cn.epicfx.winfxk.byenchant.R;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author Winfxk
 */
public class MainHandler extends Handler {
	private RelativeLayout.LayoutParams lp;
	private ProgressBar bar;
	public int y, x, z, a, q, w, e, r;
	private ImageButton imageButton;

	public MainHandler(Main main) {
		bar = (ProgressBar) main.findViewById(R.id.progressBar1);
		imageButton = (ImageButton) main.findViewById(R.id.imageButton1);
		lp = (LayoutParams) bar.getLayoutParams();
		bar.setOnLongClickListener(main);
		x = lp.bottomMargin;
		y = lp.bottomMargin * 3;
		z = lp.bottomMargin * 4;
		q = imageButton.getPaddingBottom();
		w = q / 3;
		r = q * 3;
	}

	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			lp.setMargins(lp.leftMargin, lp.topMargin, lp.rightMargin, a);
			bar.setLayoutParams(lp);
			break;
		case 1:
			imageButton.setPadding(e, e, e, e);
			break;
		case 2:
			imageButton.setPadding(q, q, q, q);
			break;
		}
		super.handleMessage(msg);
	}
}
