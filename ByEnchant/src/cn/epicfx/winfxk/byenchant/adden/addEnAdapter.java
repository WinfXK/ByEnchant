package cn.epicfx.winfxk.byenchant.adden;

import cn.epicfx.winfxk.byenchant.R;
import cn.epicfx.winfxk.byenchant.to.LayoutData;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author Winfxk
 */
public class addEnAdapter extends BaseAdapter {
	private addEnchant enchant;

	public addEnAdapter(addEnchant arg1) {
		enchant = arg1;
	}

	@Override
	public int getCount() {
		return enchant.Enchants.size();
	}

	@Override
	public Object getItem(int arg0) {
		return enchant.Enchants.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return enchant.Enchants.get(arg0).getID();
	}

	@Override
	public View getView(int id, View view, ViewGroup arg2) {
		LayoutData data;
		if (view == null) {
			view = View.inflate(enchant, R.layout.simple_item, null);
			data = new LayoutData();
			data.tv1 = (TextView) view.findViewById(R.id.textView1);
			view.setTag(data);
		}
		data = (LayoutData) view.getTag();
		data.enchant = enchant.Enchants.get(id);
		data.tv1.setText("ID: " + data.enchant.getID() + ": " + data.enchant.getName() + " (lv.1"
				+ (data.enchant.getMaxLevel() > 1 ? "-lv." + data.enchant.getMaxLevel() : "") + ")");
		view.setTag(data);
		return view;
	}

}
