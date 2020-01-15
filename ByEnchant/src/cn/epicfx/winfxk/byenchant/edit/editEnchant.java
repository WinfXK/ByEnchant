package cn.epicfx.winfxk.byenchant.edit;

import java.util.ArrayList;
import java.util.List;

import cn.epicfx.winfxk.byenchant.R;
import cn.epicfx.winfxk.byenchant.iosdialog.MyBuilder;
import cn.epicfx.winfxk.byenchant.to.EnchantName;
import cn.epicfx.winfxk.byenchant.to.LayoutData;
import cn.epicfx.winfxk.byenchant.to.Tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author Winfxk
 */
public class editEnchant extends Activity implements OnItemSelectedListener, OnSeekBarChangeListener, TextWatcher {
	public TextView enLevel, enbfb;
	public EditText editText;
	public Spinner spinner;
	public SeekBar fmLevel;
	public List<EnchantName> Enchants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addenchant);
		enLevel = (TextView) findViewById(R.id.textView1);
		enbfb = (TextView) findViewById(R.id.textView2);
		fmLevel = (SeekBar) findViewById(R.id.seekBar1);
		editText = (EditText) findViewById(R.id.editText1);
		spinner = (Spinner) findViewById(R.id.spinner1);
		Enchants = new ArrayList<>(EnchantName.getAll().values());
		spinner.setAdapter(new addEnAdapter(this));
		spinner.setOnItemSelectedListener(this);
		fmLevel.setOnSeekBarChangeListener(this);
		editText.addTextChangedListener(this);
		Intent intent = getIntent();
		EnchantName enchant = EnchantName.AQUA_AFFINITY;
		int ID = intent.getIntExtra("D", 0), index = 0;
		for (int i = 0; i < Enchants.size(); i++)
			if (ID == (enchant = Enchants.get(index = i)).getID())
				break;
		int l = intent.getIntExtra("L", 1);
		fmLevel.setProgress(l > enchant.getMaxLevel() ? enchant.getMaxLevel() : l);
		fmLevel.setEnabled(enchant.getMaxLevel() > 1);
		spinner.setSelection(index);
		editText.setText(intent.getStringExtra("%"));
	}

	public void onClick(View view) {
		Intent intent = new Intent();
		intent.putExtra("E", ((LayoutData) spinner.getSelectedView().getTag()).enchant.getID());
		intent.putExtra("L", fmLevel.getProgress());
		intent.putExtra("%", editText.getText().toString());
		setResult(2, intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		setResult(0);
		finish();
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
		if (view == null)
			return;
		int max = ((LayoutData) view.getTag()).enchant.getMaxLevel();
		fmLevel.setMax(max);
		fmLevel.setProgress(1);
		fmLevel.setEnabled(max > 1);
		enLevel.setText("附魔等级：1");
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	public void onProgressChanged(SeekBar view, int arg1, boolean arg2) {
		if (arg1 < 1)
			view.setProgress(arg1 = 1);
		enLevel.setText("附魔等级：" + arg1);
	}

	public void onImageButton(View view) {
		MyBuilder builder = new MyBuilder(this);
		builder.setMsg("这个数只能为大于零的整数！这个数越大代表出现该附魔的概率越大！具体概率受其他已添加的附魔影响。");
		builder.setTitle("帮助");
		builder.setNegativeButton("确定", null);
		builder.setCancelable(false).show();
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
	}

	@Override
	public void afterTextChanged(Editable arg0) {
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
		int i = Tool.ObjectToInt(text);
		if (i < 0)
			editText.setText(i * -1);
		else if (i < 1)
			editText.setText("1");
	}
}
