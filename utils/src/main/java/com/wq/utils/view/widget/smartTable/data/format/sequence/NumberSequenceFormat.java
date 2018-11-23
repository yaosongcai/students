package com.wq.utils.view.widget.smartTable.data.format.sequence;



/**
 * Created by huang on 2017/11/7.
 */

public class NumberSequenceFormat extends BaseSequenceFormat{

    @Override
    public String format(Integer position) {
        return String.valueOf(position);
    }


}
