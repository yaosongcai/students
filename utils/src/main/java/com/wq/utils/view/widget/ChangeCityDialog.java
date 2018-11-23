package com.wq.utils.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.wq.utils.R;
import com.wq.utils.view.widget.adapters.AbstractWheelTextAdapter;
import com.wq.utils.view.widget.adapters.AreaAdapter;
import com.wq.utils.view.widget.adapters.CitysAdapter;
import com.wq.utils.view.widget.adapters.ProvinceAdapter;
import com.wq.utils.view.widget.model.CityModel;
import com.wq.utils.view.widget.model.DistrictModel;
import com.wq.utils.view.widget.model.ProvinceModel;
import com.wq.utils.view.widget.views.OnWheelChangedListener;
import com.wq.utils.view.widget.views.WheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称： CommunityDemo
 * 类描述     地址三级联动选择器
 * 创建人：   Yaosongcai
 * 创建时间： 2018\5\21 0021 14:29
 * 修改人：   Yaosongcai
 * 修改时间： Yaosongcai or 2018\5\21 0021 14:29
 * 修改备注：
 */

public class ChangeCityDialog extends Dialog implements OnWheelChangedListener {

    private Context context;
    private WheelView wvprovince;
    private WheelView wvcity;
    private WheelView wvcounty;
    private TextView btn_myinfo_cancel;
    private TextView btn_myinfo_sure;
    private List<ProvinceModel> provinceDatas = new ArrayList<>();
    private List<CityModel> cityDatas = new ArrayList<>();
    private List<DistrictModel> districtDatas = new ArrayList<>();
    private ProvinceAdapter provinceAdapter;
    private CitysAdapter citysAdapter;
    private AreaAdapter areaAdapter;
    private SQLiteDatabase db;
    private CityDataHelper dataHelper;
    private String mCurrentProvince;
    private String mCurrentCity;
    private String mCurrentDistrict;
    private int maxsize = 18;
    private int minsize = 14;

    private OnAddressListener onAddressListener;

    public ChangeCityDialog(@NonNull Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myinfo_changecity);

        wvprovince = (WheelView) findViewById(R.id.wv_adress_province);
        wvcity = (WheelView) findViewById(R.id.wv_address_city);
        wvcounty = (WheelView) findViewById(R.id.wv_address_county);
        btn_myinfo_cancel = (TextView) findViewById(R.id.btn_myinfo_cancel);
        btn_myinfo_sure = (TextView) findViewById(R.id.btn_myinfo_sure);

        //设置可见条目数量
        wvprovince.setVisibleItems(5);
        wvcity.setVisibleItems(5);
        wvcounty.setVisibleItems(5);

        //设置change事件
        wvprovince.addChangingListener(this);
        wvcounty.addChangingListener(this);
        wvcity.addChangingListener(this);

        btn_myinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_myinfo_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressListener.onAddressListener(mCurrentProvince, mCurrentCity, mCurrentDistrict);
                dismiss();
            }
        });

        initpopData();

    }

    private void initpopData() {
        //初始化数据
        dataHelper = CityDataHelper.getInstance(context);
        db = dataHelper.openDataBase();
        provinceDatas = dataHelper.getProvice(db);
        if (provinceDatas.size() > 0) {

            //弹出popup时，省wheelview中当前的省其实就是省集合的第一个
            mCurrentProvince = provinceDatas.get(0).getName();

            //根据省cityid查询到第一个省下面市的集合
            cityDatas = dataHelper.getCityByParentId(db, provinceDatas.get(0).getCityID() + "");
        }
        if (cityDatas.size() > 0) {
            //根据市cityid查询到第一个市集合下面区的集合
            districtDatas = dataHelper.getDistrictById(db, cityDatas.get(0).getCityID() + "");

        }
        //wheelview的适配器代码
        provinceAdapter = new ProvinceAdapter(context, provinceDatas, 0, maxsize, minsize);
        wvprovince.setViewAdapter(provinceAdapter);

        updateCitys();
        updateAreas();
    }

    private void updateCitys() {
        int pCurrent = wvprovince.getCurrentItem();
        if (provinceDatas.size() > 0) {
            //这里是必须的的，上面得到的集合只是第一个省下面所有市的集合及第一个市下面所有区的集合
            //这里得到的是相应省下面对应市的集合
            cityDatas = dataHelper.getCityByParentId(db, provinceDatas.get(pCurrent).getCityID() + "");
        } else {
            cityDatas.clear();
        }
        citysAdapter = new CitysAdapter(context, cityDatas, 0, maxsize, minsize);
        wvcity.setViewAdapter(citysAdapter);
        if (cityDatas.size() > 0) {
            //默认省下面 市wheelview滑动第一个，显示第一个市
            wvcity.setCurrentItem(0);
            mCurrentCity = cityDatas.get(0).getName();
        } else {
            mCurrentCity = "";
        }
        updateAreas();
    }


    private void updateAreas() {
        int cCurrent = wvcity.getCurrentItem();
        if (cityDatas.size() > 0) {
            districtDatas = dataHelper.getDistrictById(db, cityDatas.get(cCurrent).getCityID() + "");
        } else {
            districtDatas.clear();
        }
        areaAdapter = new AreaAdapter(context, districtDatas, 0, maxsize, minsize);
        wvcounty.setViewAdapter(areaAdapter);
        if (districtDatas.size() > 0) {
            mCurrentDistrict = districtDatas.get(0).getName();
            wvcounty.setCurrentItem(0);
        } else {
            mCurrentDistrict = "";
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, AbstractWheelTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(18);
            } else {
                textvew.setTextSize(14);
            }
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {

        if (wheel == wvprovince) {
            mCurrentProvince = provinceDatas.get(newValue).getName();
            setTextviewSize(mCurrentProvince, provinceAdapter);
            updateCitys();
        }
        if (wheel == wvcity) {
            mCurrentCity = cityDatas.get(newValue).getName();
            setTextviewSize(mCurrentCity, citysAdapter);
            updateAreas();
        }
        if (wheel == wvcounty) {
            mCurrentDistrict = districtDatas.get(newValue).getName();
            setTextviewSize(mCurrentDistrict, areaAdapter);
        }
    }

    /**
     * 设置监听器
     * @param onAddressListener     监听器
     */
    public void setOnAddressListener(OnAddressListener onAddressListener) {
        this.onAddressListener = onAddressListener;
    }

    public interface OnAddressListener {
        /**
         * 地址回调
         *
         * @auther Vincent QQ:1392061759
         * create at 2018\5\21 0021  15:29
         */
        void onAddressListener(String province, String city, String county);
    }
}
