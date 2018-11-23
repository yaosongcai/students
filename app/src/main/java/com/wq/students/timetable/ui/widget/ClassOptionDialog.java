package com.wq.students.timetable.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.wq.students.R;
import com.wq.students.timetable.model.bean.StudentClassManagementBean;
import com.wq.utils.util.StringUtils;
import com.wq.utils.view.widget.adapters.AbstractWheelTextAdapter;
import com.wq.utils.view.widget.views.OnWheelChangedListener;
import com.wq.utils.view.widget.views.OnWheelScrollListener;
import com.wq.utils.view.widget.views.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：    com.wq.students.timetable.ui.widget
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/20
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/20
 * 修改备注：
 */
public class ClassOptionDialog extends Dialog {

    private ArrayList<StudentClassManagementBean> mStudentClassBeen;
    private AddressTextAdapter addressTextAdapter;
    private int maxsize = 24;
    private int minsize = 14;
    private String strLetter;
    private OnChangeListener onChangeListener;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.wv_address_province)
    WheelView wv_address_province;

    public ClassOptionDialog(@NonNull Context context) {
        super(context, R.style.ShareDialog);
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_myinfo_changesex,null);
        ButterKnife.bind(this,view);
        setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        addressTextAdapter = new AddressTextAdapter(getContext(),mStudentClassBeen,0,maxsize,minsize);
        wv_address_province.setVisibleItems(5);
        wv_address_province.setViewAdapter(addressTextAdapter);
        wv_address_province.setCurrentItem(0);
        wv_address_province.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                strLetter = (String) addressTextAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(strLetter,addressTextAdapter);
            }
        });
        wv_address_province.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) addressTextAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, addressTextAdapter);
            }
        });

    }

    @OnClick({R.id.btn_myinfo_cancel,R.id.btn_myinfo_sure})
    void click(View view){
        switch (view.getId()){
            case R.id.btn_myinfo_cancel:
                dismiss();
                break;
            case R.id.btn_myinfo_sure:
                if (onChangeListener != null){
                    onChangeListener.onChange(mStudentClassBeen.get(getProvinceItem(strLetter)));
                }
                dismiss();
                break;
        }
    }

    public void setDatas(List<StudentClassManagementBean> list){
        if (this.mStudentClassBeen == null){
            this.mStudentClassBeen = new ArrayList<>();
        }
        this.mStudentClassBeen.clear();
        this.mStudentClassBeen.addAll(list);

        if (list.size() > 0){
            strLetter = StringUtils.null2Length0(list.get(0).getGrade_name());
        }
    }

    private int getProvinceItem(String province) {
        int provinceIndex = 0;
        boolean noprovince = true;
        for (int i = 0; i < mStudentClassBeen.size(); i++) {
            if (province.equals(StringUtils.null2Length0(mStudentClassBeen.get(i).getClassCode()))) {
                noprovince = false;
                return provinceIndex;
            } else {
                provinceIndex++;
            }
        }
        if (noprovince) {
            return 0;
        }
        return provinceIndex;
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

    private class AddressTextAdapter extends AbstractWheelTextAdapter{
        ArrayList<StudentClassManagementBean> list;

        protected AddressTextAdapter(Context context, ArrayList<StudentClassManagementBean> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index).getClassCode();
        }
    }

    public interface OnChangeListener{
        /**
         * 滚动数据回调
         * @param bean
         * @author ysc
         * @time 2018/11/20 16:25
         */
        void onChange(StudentClassManagementBean bean);
    }
}
