package com.wq.students.base.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wq.students.base.utils.baseEntity.ResultBean;
import com.wq.utils.util.ActivityUtils;

import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 项目名称：    com.wq.students.base.utils
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/9
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/9
 * 修改备注：
 */
public class OnSuccessAndFaultSub<T> extends DisposableObserver<ResponseBody> implements ProgressCancelListener {
    /**
     * 是否需要显示默认Loading
     */
    private boolean showProgress = true;
    private OnSuccessAndFaultListener<T> mOnSuccessAndFaultListener;

    private Context context;
    private ProgressDialog progressDialog;
    private Gson gson;
    private Class<T> cls;
    private Type typeOfT;


    /**
     * @param mOnSuccessAndFaultListener 成功回调监听
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener<T> mOnSuccessAndFaultListener,Class<T> cls) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        gson = new Gson();
        this.cls = cls;
    }

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener<T> mOnSuccessAndFaultListener, Type typeOfT) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        gson = new Gson();
        this.typeOfT = typeOfT;
    }

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener<T> mOnSuccessAndFaultListener, Type typeOfT,boolean showProgress){
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        gson = new Gson();
        this.typeOfT = typeOfT;
        progressDialog = new ProgressDialog(ActivityUtils.getTopActivity());
        this.showProgress = showProgress;
    }

    /**
     * @param mOnSuccessAndFaultListener 成功回调监听
     * @param context 上下文
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener<T> mOnSuccessAndFaultListener, Context context) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        gson = new Gson();
    }


    /**
     * @param mOnSuccessAndFaultListener 成功回调监听
     * @param context 上下文
     * @param showProgress 是否需要显示默认Loading
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener<T> mOnSuccessAndFaultListener, Context context, boolean showProgress) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        this.showProgress = showProgress;
        gson = new Gson();
    }


    private void showProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.setMessage("正在加载，请稍候...");
            progressDialog.show();
        }
    }


    private void dismissProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.dismiss();
        }
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }


    /**
     * 完成，隐藏ProgressDialog
     */
    @Override public void onComplete() {
        dismissProgressDialog();
        progressDialog = null;
    }


    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        try {

            if (e instanceof SocketTimeoutException) {//请求超时
                mOnSuccessAndFaultListener.onFault("请求超时");
            } else if (e instanceof ConnectException) {//网络连接超时
                //                ToastManager.showShortToast("网络连接超时");
                mOnSuccessAndFaultListener.onFault("网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                //                ToastManager.showShortToast("安全证书异常");
                mOnSuccessAndFaultListener.onFault("安全证书异常");
            } else if (e instanceof HttpException) {//请求的地址不存在
                int code = ((HttpException) e).code();
                if (code == 504) {
                    //                    ToastManager.showShortToast("网络异常，请检查您的网络状态");
                    mOnSuccessAndFaultListener.onFault("网络异常，请检查您的网络状态");
                } else if (code == 404) {
                    //                    ToastManager.showShortToast("请求的地址不存在");
                    mOnSuccessAndFaultListener.onFault("请求的地址不存在");
                } else {
                    //                    ToastManager.showShortToast("请求失败");
                    mOnSuccessAndFaultListener.onFault("请求失败");
                }
            } else if (e instanceof UnknownHostException) {//域名解析失败
                //                ToastManager.showShortToast("域名解析失败");
                mOnSuccessAndFaultListener.onFault("域名解析失败");
            } else {
                //                ToastManager.showShortToast("error:" + e.getMessage());
                mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + e.getMessage());
            //            mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
            dismissProgressDialog();
            progressDialog = null;

        }

    }


    /**
     * 当result等于1回调给调用者，否则自动显示错误信息，若错误信息为401跳转登录页面。
     * ResponseBody  body = response.body();//获取响应体
     * InputStream inputStream = body.byteStream();//获取输入流
     * byte[] bytes = body.bytes();//获取字节数组
     * String str = body.string();//获取字符串数据
     */
    @Override
    public void onNext(ResponseBody body) {

        String result = null;

        try {
            result = body.string();
//            final String result = CompressUtils.decompress(body.byteStream());
            Log.e("body", result);
            ResultBean<T> resultBean = gson.fromJson(result,ResultBean.class);
            if (result != null && resultBean.getRETURN() == null){
                mOnSuccessAndFaultListener.onSuccess((T) result);
            }else if (result == null && resultBean.getRETURN() == null){
                Log.e("OnSuccessAndFaultSub", "返回数据为空！！！");
                mOnSuccessAndFaultListener.onFault("返回数据异常！");
                return;
            }else if ( resultBean.getRETURN().equals("BOK")) {
                result = null;
                if (typeOfT != null){
                    mOnSuccessAndFaultListener.onSuccess((T) gson.fromJson(gson.toJson(resultBean.getDATA()),typeOfT));
                } else {
                    mOnSuccessAndFaultListener.onSuccess(gson.fromJson(gson.toJson(resultBean.getDATA()),cls));
                }
            } else if (resultBean.getRETURN().equals("ERR")){
                mOnSuccessAndFaultListener.onFault(resultBean.getMESSAGE());
            }else {
                mOnSuccessAndFaultListener.onFault(resultBean.getMESSAGE());
                Log.e("OnSuccessAndFaultSub", "errorMsg: " + resultBean.getMESSAGE());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (result != null){
                mOnSuccessAndFaultListener.onSuccess((T) result);
                return;
            }
            mOnSuccessAndFaultListener.onFault("数据解析错误");
        }
    }


    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }
}
