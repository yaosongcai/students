package com.wq.students.base.utils.version;


import android.content.Context;

import com.google.gson.Gson;
import com.wq.students.base.integration.RepositoryManager;
import com.wq.students.base.utils.OnSuccessAndFaultListener;
import com.wq.students.base.utils.OnSuccessAndFaultSub;
import com.wq.students.base.utils.version.model.bean.VersionBean;
import com.wq.students.base.utils.version.model.model.VersionModel;
import com.wq.utils.util.AppUtils;
import com.wq.utils.util.ToastUtils;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

public class VersionUtils {

    private Context mContext;

    private VersionModel versionModel;

//    private VersionDialog dialog1;

    public VersionUtils(Context context) {
        this.mContext = context;
    }

    public VersionUtils() {
//        versionModel = new VersionModel();
    }

    /**
     * @param isForce 是否强制更新
     * @param isToast 是否弹窗
     * @param type    类型
     * @author ysc
     * @time 2018/9/10 11:49
     */
    public void checkUpdate(final int type, final boolean isToast, final boolean isForce) {
        final int versionCode = AppUtils.getAppVersionCode();
        VersionModel model = new VersionModel(new RepositoryManager());
        DisposableObserver<ResponseBody> d = new OnSuccessAndFaultSub<String>(new OnSuccessAndFaultListener<String>() {
            @Override
            public void onSuccess(String result) {
                VersionBean bean = new Gson().fromJson(result, VersionBean.class);
                if (bean.getTotal() == 1) {
//                    versionDialog(bean, isForce);
                } else {
                    if (isToast) {
                        ToastUtils.showShort("当前已经是最新版本了");
                    }
                }
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtils.showShort(errorMsg);
            }
        },String.class);
        model.detectionVersion(String.valueOf(versionCode), "Android", d);

    }

//    private void versionDialog(final VersionBean bean,boolean isForce) {
//
//        if (dialog1 == null) {
//            dialog1 = new VersionDialog(mContext);
//            dialog1.setOnUpdateListener(new VersionDialog.OnUpdateListener() {
//                @Override
//                public void onUpdateListener() {
//                    String destFileName = "智慧校园.apk";
//                    dialog1.setState(1,bean.getUrl(),destFileName,true);
//                    dialog1.show();
//                }
//            });
//        }
//
//        if (!dialog1.isShowing()){
//            dialog1.setState(0,null,null,isForce);
//            dialog1.show();
//        }
//
//    }

//    public void downloadApk(final Context context, String url, String apkName) {
//        HttpLoadOrDorwnUtils.getInstance(context).downLoadApkProgresses(url, apkName, new HttpLoadOrDorwnUtils.HttpUpLoadCallBackListener() {
//            @Override
//            public void onStartLoadFile() {
//
//            }
//
//            @Override
//            public void onFinishLoadFile() {
//
//            }
//
//            @Override
//            public void onProgress(long total, float progress) {
//
//            }
//
//            @Override
//            public void onSuccess(String response) {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
//        HttpUtils.downloadFile(context, url, destFileDir, destFileName, true, new FileCallBack(destFileDir, destFileName) {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//            }
//
//            @Override
//            public void onResponse(File response, int id) {
//                HttpUtils.installApp(context, response);//安装APK
//            }
//        });
//    }

}
