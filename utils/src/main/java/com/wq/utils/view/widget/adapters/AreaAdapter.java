package com.wq.utils.view.widget.adapters;

import android.content.Context;

import com.wq.utils.R;
import com.wq.utils.view.widget.model.DistrictModel;

import java.util.List;


/**
 * Created by xuan on 16/1/7.
 */
public class AreaAdapter extends AbstractWheelTextAdapter {
    private List<DistrictModel> mList;
    private Context mContext;
    public AreaAdapter(Context context, List<DistrictModel> list, int current, int maxsize, int minsize) {
        super(context, R.layout.item_birth_year, NO_RESOURCE, current, maxsize, minsize);
        mList=list;
        mContext=context;
        setItemTextResource(R.id.tempValue);
    }

    @Override
    protected CharSequence getItemText(int index) {
        DistrictModel districtModel=mList.get(index);
        return districtModel.getName();
    }

    @Override
    public int getItemsCount() {
        return mList.size();
    }
}
