package com.wq.utils.view.widget.adapters;

import android.content.Context;

import com.wq.utils.R;
import com.wq.utils.view.widget.model.ProvinceModel;

import java.util.List;


/**
 * Created by xuan on 16/1/7.
 */
public class ProvinceAdapter extends AbstractWheelTextAdapter {
    public List<ProvinceModel> mList;
    private Context mContext;
    public ProvinceAdapter(Context context, List<ProvinceModel> list, int currentItem, int maxsize, int minsize) {
        super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
        this.mList = list;
        this.mContext = context;
        setItemTextResource(R.id.tempValue);
    }

    @Override
    protected CharSequence getItemText(int index) {
        ProvinceModel provinceModel=mList.get(index);
        return provinceModel.getName();
    }

    @Override
    public int getItemsCount() {
        return mList.size();
    }
}
