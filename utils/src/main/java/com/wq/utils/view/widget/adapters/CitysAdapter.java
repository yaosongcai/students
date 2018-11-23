package com.wq.utils.view.widget.adapters;

import android.content.Context;

import com.wq.utils.R;
import com.wq.utils.view.widget.model.CityModel;

import java.util.List;


/**
 * Created by xuan on 16/1/7.
 */
public class CitysAdapter extends AbstractWheelTextAdapter {
    public List<CityModel> mList;
    private Context mContext;
    public CitysAdapter(Context context, List<CityModel> list,int current,int maxsize,int minsize) {
        super(context, R.layout.item_birth_year, NO_RESOURCE, current, maxsize, minsize);
        mList=list;
        mContext=context;
        setItemTextResource(R.id.tempValue);
    }

    @Override
    protected CharSequence getItemText(int index) {
        CityModel cityModel=mList.get(index);
        return cityModel.getName();
    }

    @Override
    public int getItemsCount() {
        return mList.size();
    }
}
