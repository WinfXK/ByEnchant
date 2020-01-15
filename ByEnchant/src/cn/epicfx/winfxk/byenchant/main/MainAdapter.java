package cn.epicfx.winfxk.byenchant.main;

import java.util.Map;

import cn.epicfx.winfxk.byenchant.R;
import cn.epicfx.winfxk.byenchant.to.EnchantName;
import cn.epicfx.winfxk.byenchant.to.LayoutData;
import cn.epicfx.winfxk.byenchant.to.Tool;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Winfxk
 */
public class MainAdapter extends BaseAdapter {
	private Main main;

	public MainAdapter(Main main) {
		this.main = main;
	}

	@Override
	public int getCount() {
		return main.data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return main.data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int id, View view, ViewGroup arg2) {
		LayoutData data;
		if (view == null) {
			view = View.inflate(main, R.layout.simple_item2, null);
			data = new LayoutData();
			data.iv = (ImageView) view.findViewById(R.id.imageView1);
			data.tv1 = (TextView) view.findViewById(R.id.textView1);
			data.tv2 = (TextView) view.findViewById(R.id.textView2);
			view.setTag(data);
		}
		data = (LayoutData) view.getTag();
		data.iv.setImageResource((id % 2 == 0) ? R.drawable.ic_launcher : R.drawable.ico);
		Map<String, Object> map = main.data.get(id);
		EnchantName enchant = EnchantName.getAll().get(Tool.ObjectToInt(map.get("D")));
		map.put("E", enchant);
		data.enchant = enchant;
		data.map = map;
		data.tv1.setText("Lv." + map.get("L") + "  " + enchant.getName() + "(" + enchant.getID() + ")");
		data.tv2.setText("Lv." + map.get("L") + "    附魔到该附魔的概率为：" + main.getBfb(map.get("%")));
		view.setTag(data);
		return view;
	}

}
