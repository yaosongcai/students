package com.wq.utils.imagetrans.listener;

/**
 * Created by liuting on 17/5/26.
 */

public interface OnPullCloseListener {

    void onClose();

    void onPull(float range);

    void onCancel();
}
