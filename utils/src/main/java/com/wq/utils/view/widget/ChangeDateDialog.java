package com.wq.utils.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * 鏇存敼灏侀潰瀵硅瘽妗�
 *
 * @author ywl
 */
public class ChangeDateDialog extends Dialog implements View.OnClickListener {

    private WheelView wvNian;
    private WheelView wvYue;
    private WheelView wvRi;
    private View lyChangeAddress;
    private View lyChangeAddressChild;
    private TextView btnSure;
    private TextView btnCancel;
    private TextView title;
    private Context context;
    private ArrayList<String> nianLetters = new ArrayList<String>();
    private ArrayList<String> yueLetters = new ArrayList<String>();
    private ArrayList<String> riLetters = new ArrayList<String>();
    private AddressTextAdapter nianAdapter;
    private AddressTextAdapter yueAdapter;
    private AddressTextAdapter riAdapter;
    private String nianLetter = "2018";
    private String yueLetter = "01";
    private String riLetter = "01";
    private int nianNum = 2018;
    private int yueNum = 1;
    private int riNum = 01;
    private String nian = "2018";
    private String yue = "01";
    private String ri = "01";
    private Date date = new Date(System.currentTimeMillis());
    private OnAddressCListener onAddressCListener;

    private String titles = "选择生日";

    private int maxsize = 24;
    private int minsize = 14;
    private int dates;

    private int indexNian;

    public ChangeDateDialog(Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myinfo_changedate);
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        wvNian = (WheelView) findViewById(R.id.wv_address_nian);
        wvYue = (WheelView) findViewById(R.id.wv_address_yue);
        wvRi = (WheelView) findViewById(R.id.wv_address_ri);
        lyChangeAddress = findViewById(R.id.ly_myinfo_changedate);
        lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
        btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);
        title = (TextView) findViewById(R.id.title);
        title.setText(titles);

        lyChangeAddress.setOnClickListener(this);
        lyChangeAddressChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        initDatas();
        nianAdapter = new AddressTextAdapter(context, nianLetters, getProvinceItem(nianLetter), maxsize, minsize);
        wvNian.setVisibleItems(5);
        wvNian.setViewAdapter(nianAdapter);
        wvNian.setCurrentItem(getProvinceItem(nianLetter));
        indexNian = wvNian.getCurrentItem();

        yueAdapter = new AddressTextAdapter(context, yueLetters, getProvinceyueItem(yueLetter), maxsize, minsize);
        wvYue.setVisibleItems(5);
        wvYue.setViewAdapter(yueAdapter);
        wvYue.setCurrentItem(getProvinceyueItem(yueLetter));
        howMuchDay(false);
        setRiAdapter(riLetters);
        wvNian.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) nianAdapter.getItemText(wheel.getCurrentItem());
                nianLetter = currentText;
                setTextviewSize(currentText, nianAdapter);
                try {
                    nianNum = Integer.parseInt(currentText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nian = currentText;
                howMuchDay(false);
                Log.i("nianNum", nianNum + "");
            }
        });

        wvNian.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                indexNian = wheel.getCurrentItem();
                String currentText = (String) nianAdapter.getItemText(wheel.getCurrentItem());
                if (wheel.getCurrentItem() >= nianLetters.size() - 1) {
                    initDatas1();
                    howMuchDay(true);
                } else {
                    initDatas();
                    howMuchDay(false);
                }
                wvYue.setViewAdapter(yueAdapter);
                wvRi.setViewAdapter(riAdapter);
                setTextviewSize(currentText, nianAdapter);
            }
        });
        wvYue.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) yueAdapter.getItemText(wheel.getCurrentItem());
                nianLetter = currentText;
                setTextviewSize(currentText, yueAdapter);
                try {
                    yueNum = Integer.parseInt(currentText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                yue = currentText;
                howMuchDay(false);
                Log.i("yueNum", yueNum + "");
            }
        });

        wvYue.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) yueAdapter.getItemText(wheel.getCurrentItem());
                if (indexNian >= nianLetters.size() - 1 && wheel.getCurrentItem() >= yueLetters.size() - 1) {
                    howMuchDay(true);
                } else
                    howMuchDay(false);
                wvRi.setViewAdapter(riAdapter);
                setTextviewSize(currentText, yueAdapter);
            }
        });
        wvRi.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) riAdapter.getItemText(wheel.getCurrentItem());
                nianLetter = currentText;
                setTextviewSize(currentText, riAdapter);
                ri = currentText;
                try {
                    riNum = Integer.parseInt(currentText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        wvRi.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) riAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, riAdapter);
            }
        });

    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    private void setRiAdapter(ArrayList<String> riLetterss) {
        riAdapter = new AddressTextAdapter(context, riLetterss, getProvinceriItem(riLetter), maxsize, minsize);
        wvRi.setVisibleItems(5);
        wvRi.setViewAdapter(riAdapter);
        wvRi.setCurrentItem(getProvinceriItem(riLetter));
    }

    private void howMuchDay(boolean isxz) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, nianNum);
        cal.set(Calendar.MONTH, yueNum - 1);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        riLetters.clear();
        int maxSize = maxDate;
        if (isxz)
            maxSize = getRiSize(maxDate);
        for (int i = 1; i < maxSize + 1; i++) {
            if (i < 10) {
                riLetters.add("0" + i);
            } else
                riLetters.add("" + i);
        }
        setRiAdapter(riLetters);
    }

    private int getRiSize(int maxDate) {
        int daySize = maxDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
            daySize = Integer.parseInt(dateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
            daySize = maxDate;
        }
        return daySize;
    }

    public int getDates(int startYear) {
        int size = 100;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            String ys = dateFormat.format(date);

            int yss = Integer.parseInt(ys);
            size = yss - startYear + 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return size;
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
        Window window = getWindow();
        window.setWindowAnimations(R.style.PopupWindowVerticalAnimation);
        super.show();
    }

    /**
     * 璁剧疆瀛椾綋澶у皬
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

    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnSure) {
            if (onAddressCListener != null) {
                onAddressCListener.onClick(nian + "-" + yue + "-" + ri);
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
     * 鍥炶皟鎺ュ彛
     *
     * @author Administrator
     */
    public interface OnAddressCListener {
        public void onClick(String province);
    }

    /**
     * 瑙ｆ瀽鏁版嵁
     */
    private void initDatas() {
        nianLetters.clear();
        yueLetters.clear();
        int sizes = 100;//getDates(1950)
        for (int i = 0; i < sizes; i++) {
            nianLetters.add(i + 1950 + "");
        }
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                yueLetters.add("0" + i);
            } else
                yueLetters.add("" + i);
        }
    }

    /**
     * 瑙ｆ瀽鏁版嵁
     */
    private void initDatas1() {
        yueLetters.clear();
        int sizes_m = 12;//getDates_m(12)
        for (int i = 1; i <= sizes_m; i++) {
            if (i < 10) {
                yueLetters.add("0" + i);
            } else
                yueLetters.add("" + i);
        }
    }

    private int getDates_m(int i) {
        int sizes = 12;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
            int ss = Integer.parseInt(dateFormat.format(date));
            sizes = ss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizes;
    }


    public void setLetter(String year, String month, String day) {
        if (year != null && year.length() > 0) {
            this.nianLetter = year;
            this.nian = year;
        }
        if (month != null && month.length() > 0) {
            this.yueLetter = month;
            this.yue = month;
        }
        if (day != null && day.length() > 0) {
            this.riLetter = day;
            this.ri = day;
        }
    }


    public int getProvinceItem(String province) {
        int size = nianLetters.size();
        int provinceIndex = 0;
        boolean noprovince = true;
        for (int i = 0; i < size; i++) {
            if (province.equals(nianLetters.get(i))) {
                noprovince = false;
                return provinceIndex;
            } else {
                provinceIndex++;
            }
        }
        if (noprovince) {
            nianLetter = "2018";
            return 0;
        }
        return provinceIndex;
    }

    public int getProvinceyueItem(String province) {
        int size = yueLetters.size();
        int provinceIndex = 0;
        boolean noprovince = true;
        for (int i = 0; i < size; i++) {
            if (province.equals(yueLetters.get(i))) {
                noprovince = false;
                return provinceIndex;
            } else {
                provinceIndex++;
            }
        }
        if (noprovince) {
            yueLetter = "01";
            return 0;
        }
        return provinceIndex;
    }

    public int getProvinceriItem(String province) {
        int size = riLetters.size();
        int provinceIndex = 0;
        boolean noprovince = true;
        for (int i = 0; i < size; i++) {
            if (province.equals(riLetters.get(i))) {
                noprovince = false;
                return provinceIndex;
            } else {
                provinceIndex++;
            }
        }
        if (noprovince) {
            riLetter = "01";
            return 0;
        }
        return provinceIndex;
    }


}