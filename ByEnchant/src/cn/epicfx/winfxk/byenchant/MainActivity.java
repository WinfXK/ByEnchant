package cn.epicfx.winfxk.byenchant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import cn.epicfx.winfxk.byenchant.iosdialog.Toast;
import cn.epicfx.winfxk.byenchant.main.Main;
import cn.epicfx.winfxk.byenchant.to.Tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	public ImageView girl, me;
	public MyHander Handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Properties config = new Properties();
		File file = new File(getFilesDir(), "Config.xk");
		if (!file.exists())
			try {
				config.setProperty("start", "b");
				config.setProperty("tip", "a");
				config.setProperty("s", "1");
				config.storeToXML(new FileOutputStream(file), null);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "写入默认配置失败！").show();
			}
		try {
			config.loadFromXML(new FileInputStream(file));
			if (Tool.objToString(config.getProperty("start"), "").equals("a")) {
				Intent intent = new Intent(this, Main.class);
				startActivity(intent);
				finish();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "配置文件载入失败！").show();
		}
		girl = (ImageView) findViewById(R.id.imageView2);
		me = (ImageView) findViewById(R.id.imageView3);
		Handler = new MyHander(this);
		new MyThread(this, 0).start();
		new MyThread(this, 1).start();
	}
}
