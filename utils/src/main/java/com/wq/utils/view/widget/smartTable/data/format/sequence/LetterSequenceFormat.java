package com.wq.utils.view.widget.smartTable.data.format.sequence;


import com.wq.utils.view.widget.smartTable.utils.LetterUtils;

/**
 * Created by huang on 2017/11/7.
 */

public class LetterSequenceFormat extends BaseSequenceFormat{

    @Override
    public String format(Integer position) {
        return LetterUtils.ToNumberSystem26(position);
    }
}
