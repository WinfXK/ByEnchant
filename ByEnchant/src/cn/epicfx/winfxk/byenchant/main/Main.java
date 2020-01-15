package cn.epicfx.winfxk.byenchant.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cn.epicfx.winfxk.byenchant.R;
import cn.epicfx.winfxk.byenchant.adden.addEnchant;
import cn.epicfx.winfxk.byenchant.edit.editEnchant;
import cn.epicfx.winfxk.byenchant.iosdialog.MyBuilder;
import cn.epicfx.winfxk.byenchant.iosdialog.Toast;
import cn.epicfx.winfxk.byenchant.to.EnchantName;
import cn.epicfx.winfxk.byenchant.to.LayoutData;
import cn.epicfx.winfxk.byenchant.to.Tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Winfxk
 */
@SuppressLint("CutPasteId")
public class Main extends Activity
		implements OnItemClickListener, OnItemLongClickListener, View.OnClickListener, OnLongClickListener {
	protected ListView lv;
	protected List<Map<String, Object>> data;
	private ListView listView;
	private LayoutData data1;
	private long zgl = 0, max;
	private TextView textView;
	private int index;
	protected MainHandler handler;
	protected MainThread thread, thread2;
	protected boolean isOK = false;
	private static final String[] QAQ = { "(；´д｀)ゞ", "(；′⌒`)", "(；д；)", "(；へ：)", "(╥╯^╰╥)", "╮(╯﹏╰）╭", "/(ㄒoㄒ)/~~",
			"┭┮﹏┭┮", "(｡•́︿•̀｡)", "((유∀유|||)) " };
	private static final String[] qwq = { "ヾ(✿ﾟ▽ﾟ)ノ", "٩(๑❛ᴗ❛๑)۶", "(๑╹◡╹)ﾉ\"\"\"", "ヾ(^∀^)ﾉ", "ヾ(o´∀｀o)ﾉ ",
			"(づ｡◕ᴗᴗ◕｡)づ", "(⁎˃ᴗ˂⁎)", "(≧∇≦)ﾉ", "♪（＾∀＾●）ﾉ", "●ヽ(ﾟ∀ﾟ)ﾉ● ", "(σ´∀`)σ ", "ﾍ(｀▽´*)", "(*＾ワ＾*)", "(＾ω＾)",
			"o(*￣▽￣*)o ", "(☆^O^☆)", "\\(^o^)/", "‧★,:*:‧\\(￣▽￣)/‧:*‧°★*　 ", "(＊＞︶＜＊)", "o(*////▽////*)q", "٩(❛ั︶❛ั＊)",
			"ヾ(*Ő౪Ő*)", "φ(>ω<*) ", "ヾ(=･ω･=)o", "(*/ω＼*)", "(〃´-ω･) " };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Properties config = new Properties();
		final File file = new File(getFilesDir(), "Config.xk");
		if (!file.exists())
			try {
				config.setProperty("start", "b");
				config.setProperty("tip", "a");
				config.storeToXML(new FileOutputStream(file), null);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "写入默认配置失败！").show();
			}
		try {
			config.loadFromXML(new FileInputStream(file));
			if (Tool.objToString(config.getProperty("tip"), "").equals("a")) {
				MyBuilder builder = new MyBuilder(this);
				builder.setTitle("提示");
				builder.setCancelable(false);
				builder.setMsg(
						"欢迎使用由冰月编写的App\n\n本软件完全免费！如果你是给了钱的，那么你很可能被坑了！\n\n点击右上角“+”可以添加附魔项目，长按帅逼凯图标可[设置/取消]快速启动\n\n如有任何问题请联系本帅比！");
				builder.setNegativeButton("确定", new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						config.setProperty("s", Tool.ObjectToInt(config.getProperty("s"), 0) + 1 + "");
						try {
							config.storeToXML(new FileOutputStream(file), null);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				if (Tool.ObjectToInt(config.getProperty("s"), 0) > 5)
					builder.setPositiveButton("不再显示", new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							config.setProperty("tip", "b");
							try {
								config.storeToXML(new FileOutputStream(file), null);
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(Main.this, "保存错误！").show();
							}
						}
					});
				builder.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "配置文件载入失败！").show();
		}
		lv = (ListView) findViewById(R.id.listView1);
		data = new ArrayList<>();
		listView = (ListView) findViewById(R.id.listView1);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
		textView = (TextView) findViewById(R.id.textView1);
		if (Tool.getRand(0, 5) == 1)
			thread = new MainThread(this, 0);
		handler = new MainHandler(this);
		new MainThread(this, 2).start();
		if (thread != null)
			thread.start();
	}

	public void onAll(View v) {
		MyBuilder builder = new MyBuilder(this);
		builder.setTitle("提示");
		builder.setMsg("您想要系统自动生成一套完整的附魔方案吗？");
		builder.setCancelable(false);
		builder.setNegativeButton("确定", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				data = new ArrayList<>();
				zgl = 0;
				int level = 0, b;
				Map<String, Object> map;
				for (EnchantName enchant : EnchantName.getAll().values()) {
					level = enchant.getMaxLevel();
					for (int i = level; i > 0; i--) {
						map = new HashMap<>();
						b = (int) Math.pow(2, (level - i) + 1);
						map.put("D", enchant.getID());
						map.put("L", i);
						map.put("%", b);
						zgl += b;
						data.add(map);
					}
				}
				reloadAdapter();
			}
		});
		builder.setPositiveButton("取消", null);
		builder.show();
	}

	public void onAdd(View v) {
		Intent intent = new Intent(this, addEnchant.class);
		startActivityForResult(intent, 1);
	}

	@Override
	public void onBackPressed() {
		MyBuilder builder = new MyBuilder(this);
		builder.setTitle("提示");
		builder.setCancelable(false);
		builder.setMsg("您确定要退出了吗？未保存的数据将会删除！");
		builder.setNegativeButton("确定", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(Main.this, QAQ[Tool.getRand(0, QAQ.length - 1)]).show();
				finish();
			}
		});
		builder.setPositiveButton("取消", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(Main.this, qwq[Tool.getRand(0, qwq.length - 1)]).show();
			}
		});
		builder.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 0 || data == null)
			return;
		Map<String, Object> map = new HashMap<>();
		long i = Tool.objToLong(data.getStringExtra("%"));
		map.put("D", data.getIntExtra("E", 0));
		map.put("L", data.getIntExtra("L", 0));
		map.put("%", i);
		zgl += i;
		switch (resultCode) {
		case 1:
			this.data.add(map);
			break;
		case 2:
			this.data.set(index, map);
			zgl -= max;
			break;
		}
		reloadAdapter();
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void onSave(View v) {
		String string = "";
		for (Map<String, Object> map : data)
			string += (string.isEmpty() ? "" : ";") + map.get("D") + ">" + map.get("L") + ">" + map.get("%");
		Tool.putTextIntoClip(this, string + ";");
		Toast.makeText(this, "已写入粘贴板！").show();
	}

	public void onAddEdit(View v) {
		Builder builder = new Builder(this);
		View view = View.inflate(this, R.layout.edittext, null);
		final EditText editText = (EditText) view.findViewById(R.id.editText1);
		builder.setView(view);
		builder.setTitle("手动输入文本");
		builder.setNeutralButton("确定且覆盖", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				start(editText.getText().toString(), true);
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				start(editText.getText().toString(), false);
			}
		});
		builder.setNegativeButton("取消", null);
		builder.setCancelable(false);
		builder.show();
	}

	private void start(String string, boolean b) {
		if (string == null || string.isEmpty()) {
			MyBuilder builder = new MyBuilder(this);
			builder.setTitle("提示");
			builder.setMsg("请输入想要导入编辑的文本！");
			builder.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					onAddEdit(arg0);
				}
			});
			builder.show();
			return;
		}
		String[] strings = string.split(";");
		int index = 0, hf;
		long a;
		List<Map<String, Object>> list = data;
		a = zgl;
		if (b) {
			data = new ArrayList<>();
			zgl = 0;
		}
		Map<String, Object> map;
		String stringxxx = "";
		for (String string2 : strings) {
			if (string2 == null || string2.isEmpty())
				continue;
			String[] strings1 = string2.split(">");
			if (strings1.length < 1)
				continue;
			map = new HashMap<>();
			hf = Tool.ObjectToInt(strings1.length > 2 ? strings1[2] : 1);
			map.put("D", strings1[0]);
			map.put("L", strings1.length > 1 ? strings1[1] : 1);
			map.put("%", hf);
			zgl += hf;
			stringxxx += (stringxxx.isEmpty() ? "" : "\n") + map.get("D") + " " + map.get("L") + " " + map.get("%");
			data.add(map);
			index++;
		}
		if (index == 0) {
			Toast.makeText(this, "未解析到任何有关数据！").show();
			data = list;
			zgl = a;
		} else {
			Toast.makeText(this, (b ? "设置" : "添加") + "完成！共找到" + index + "数据！").show();
			reloadAdapter();
		}
	}

	public void onRemove(View v) {
		MyBuilder builder = new MyBuilder(this);
		if (data.size() > 0) {
			builder.setTitle("警告");
			builder.setMsg("确定要删除所有数据吗？");
			builder.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					data = new ArrayList<>();
					zgl = 0;
					reloadAdapter();
				}
			});
		} else {
			builder.setTitle("提示");
			builder.setMsg("没有更多数据了...");
		}
		builder.setNegativeButton("取消", null);
		builder.setCancelable(false);
		builder.show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		LayoutData data = (LayoutData) arg1.getTag();
		Toast.makeText(this, "已删除第" + (arg2 + 1) + "个项目（" + data.enchant.getName() + "）！").show();
		zgl -= Tool.objToLong(data.map.get("%"));
		this.data.remove(arg2);
		reloadAdapter();
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, final int arg2, long arg3) {
		data1 = (LayoutData) view.getTag();
		MyBuilder builder = new MyBuilder(this);
		builder.setTitle(data1.enchant.getName());
		builder.setMsg("Name：" + data1.enchant.getName() + "\nID：" + data1.enchant.getID() + "\n附魔等级："
				+ data1.map.get("L") + "\n附魔概率：" + getBfb(data1.map.get("%")));
		builder.setNegativeButton("编辑", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Main.this, editEnchant.class);
				intent.putExtra("D", data1.enchant.getID());
				intent.putExtra("L", data1.map.get("L").toString());
				intent.putExtra("%", data1.map.get("%").toString());
				index = arg2;
				max = Tool.objToLong(data1.map.get("%"));
				startActivityForResult(intent, 1);
			}
		});
		builder.setPositiveButton("复制数据", this).show();
	}

	protected String getBfb(Object obj) {
		long f = Tool.ObjectToInt(obj);
		long gya = Tool.getGys(f, zgl);
		long fz = f / gya, fm = zgl / gya;
		return fz + "/" + fm + " (" + Tool.Double2(((double) fz / fm) * 100) + "%)";
	}

	private void reloadAdapter() {
		listView.setAdapter(new MainAdapter(this));
		textView.setText("性能影响率：" + zgl);
	}

	@Override
	public void onClick(View arg0) {
		if (data1 == null)
			return;
		Tool.putTextIntoClip(this, data1.enchant.getID() + ">" + data1.map.get("L") + ">" + data1.map.get("%") + ";");
		Toast.makeText(this, "已写入粘贴板！").show();
	}

	@Override
	public boolean onLongClick(View arg0) {
		Properties config = new Properties();
		File file = new File(getFilesDir(), "Config.xk");
		if (!file.exists())
			try {
				config.storeToXML(new FileOutputStream(file), null);
			} catch (Exception e) {
				e.printStackTrace();
				MyBuilder builder = new MyBuilder(this);
				builder.setTitle("提示");
				builder.setMsg("写入默认配置文件失败！" + e.getMessage());
				builder.setPositiveButton("确定", null);
				builder.setCancelable(false);
				builder.show();
				return true;
			}
		try {
			config.loadFromXML(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
			MyBuilder builder = new MyBuilder(this);
			builder.setTitle("提示");
			builder.setMsg("读取默认配置文件失败！" + e.getMessage());
			builder.setPositiveButton("确定", null);
			builder.setCancelable(false);
			builder.show();
			return true;
		}
		boolean b = !Tool.objToString(config.getProperty("start"), "").equals("a");
		config.setProperty("start", b ? "a" : "b");
		try {
			config.storeToXML(new FileOutputStream(file), null);
		} catch (Exception e) {
			e.printStackTrace();
			MyBuilder builder = new MyBuilder(this);
			builder.setTitle("提示");
			builder.setMsg("写入快速启动失败！" + e.getMessage());
			builder.setPositiveButton("确定", null);
			builder.setCancelable(false);
			builder.show();
			return true;
		}
		Toast.makeText(this, "已" + (b ? "设置" : "取消") + "快速启动").show();
		return false;
	}

	public void onBy(View view) {
		MyBuilder builder = new MyBuilder(this);
		builder.setTitle("伟大的作者");
		builder.setMsg(
				"作者：冰月\n\nEmail： Winfxk@qq.com\n\nQQ：981393176\n\nGitHub：https://github.com/WinfXK\n\nWeb：http://winfxk.epicfx.cn");
		builder.setNegativeButton("添加作者", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!joinQQ())
					Toast.makeText(Main.this, "无法唤起手机QQ！请安装或升级您的手机QQ").show();
			}
		});
		builder.setPositiveButton("添加群聊", new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!joinQQGroup("JIH6f0RBADknfEUbgUUYOzJw0GPwqdFH"))
					Toast.makeText(Main.this, "无法唤起手机QQ！请安装或升级您的手机QQ").show();
			}
		});
		builder.show();
	}

	public boolean joinQQ() {
		try {
			String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + (Tool.getRand(1, 2) == 1 ? "2508543202" : "981393176");
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean joinQQGroup(String key) {
		Intent intent = new Intent();
		intent.setData(Uri.parse(
				"mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D"
						+ key));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try {
			startActivity(intent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
