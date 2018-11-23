package com.wq.utils.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wq.utils.R;
import com.wq.utils.view.widget.adapters.AbstractWheelTextAdapter;
import com.wq.utils.view.widget.views.OnWheelChangedListener;
import com.wq.utils.view.widget.views.OnWheelScrollListener;
import com.wq.utils.view.widget.views.WheelView;

import java.util.ArrayList;


/**
 * 更改封面对话框
 * 
 * @author ywl
 *
 */
public class ChangeSexDialog extends Dialog implements View.OnClickListener {

	private WheelView wvProvince;
	private View lyChangeAddress;
	private View lyChangeAddressChild;
	private TextView btnSure;
	private TextView btnCancel;
	private Context context;
	private ArrayList<String> arrLetters = new ArrayList<String>();
	private AddressTextAdapter provinceAdapter;
	private String strLetter = "C";
	private OnAddressCListener2 onAddressCListener;

	private int maxsize = 24;
	private int minsize = 14;

	public ChangeSexDialog(Context context) {
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_myinfo_changesex);
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) 
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

		wvProvince = (WheelView) findViewById(R.id.wv_address_province);
		lyChangeAddress = findViewById(R.id.ly_myinfo_changeaddress);
		lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
		btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

		lyChangeAddress.setOnClickListener(this);
		lyChangeAddressChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		initDatas();
		provinceAdapter = new AddressTextAdapter(context, arrLetters, getProvinceItem(strLetter), maxsize, minsize);
		wvProvince.setVisibleItems(5);
		wvProvince.setViewAdapter(provinceAdapter);
		wvProvince.setCurrentItem(getProvinceItem(strLetter));
		wvProvince.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				strLetter = currentText;
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		wvProvince.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}
	public void show() {
		Window window =getWindow(); //得到对话框  
		window.setWindowAnimations(R.style.PopupWindowVerticalAnimation); //设置窗口弹出动画 
		super.show();
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

	public void setAddresskListener(OnAddressCListener2 onAddressCListener2) {
		this.onAddressCListener = onAddressCListener2;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSure) {
			if (onAddressCListener != null) {
				onAddressCListener.onClick(strLetter);
			}
		} else if (v == btnCancel) {

		} else if (v == lyChangeAddressChild) {
			return;
		} else {
			dismiss();
		}
		dismiss();
	}

	/**
	 * 回调接口
	 * 
	 * @author Administrator
	 *
	 */
	public interface OnAddressCListener2 {
		public void onClick(String province);
	}

	/**
	 * 解析数据
	 */
	private void initDatas() {
		arrLetters.clear();
		arrLetters.add("男");
		arrLetters.add("女");
	}



	public void setLetter(String Letter) {
		if (Letter != null && Letter.length() > 0) {
			this.strLetter = Letter;
		}
	}


	public int getProvinceItem(String province) {
		int size = arrLetters.size();
		int provinceIndex = 0;
		boolean noprovince = true;
		for (int i = 0; i < size; i++) {
			if (province.equals(arrLetters.get(i))) {
				noprovince = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (noprovince) {
			strLetter = "男";
			return 0;
		}
		return provinceIndex;
	}


}