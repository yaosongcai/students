package com.wq.students.login.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wq.students.R;
import com.wq.students.base.base.BaseActivity;
import com.wq.students.base.di.component.AppComponent;
import com.wq.students.base.integration.cache.IntelligentCache;
import com.wq.students.base.utils.ArmsUtils;
import com.wq.students.component.DaggerLoginComponent;
import com.wq.students.home.ui.activity.HomeActivity;
import com.wq.students.login.contracts.LoginContract;
import com.wq.students.login.presenter.LoginPersenter;
import com.wq.students.module.LoginModule;
import com.wq.utils.constant.PermissionConstants;
import com.wq.utils.util.PermissionUtils;

import javax.inject.Inject;

import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginPersenter> implements LoginContract.View {

    @Inject
    RxPermissions mRxPermissions;

    //更新文件是否下载成功
    private boolean isDownLoad;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_login;
    }

    @OnClick({R.id.button_login})
    void  onClick(View view){
        switch (view.getId()){
            case R.id.button_login:
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mRxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        if (permission.granted) {
//                            // 用户已经同意该权限
//                            Log.d(TAG, permission.name + " is granted.");
//                        } else if (permission.shouldShowRequestPermissionRationale) {
//                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
//                        } else {
//                            // 用户拒绝了该权限，并且选中『不再询问』
//                            Log.d(TAG, permission.name + " is denied.");
//                        }
//                    }
//                });
        if (!PermissionUtils.isGranted(PermissionConstants.STORAGE)) {
            PermissionUtils.permission(PermissionConstants.STORAGE)
                    .rationale(new PermissionUtils.OnRationaleListener() {
                        @Override
                        public void rationale(ShouldRequest shouldRequest) {
                            shouldRequest.again(true);//重新请求
                        }
                    })
                    .callback(new PermissionUtils.SimpleCallback() {
                        @Override
                        public void onGranted() {
                            mPresenter.checkVersion();
                        }

                        @Override
                        public void onDenied() {

                        }
                    })
                    .request();
            return;
        }

        mPresenter.checkVersion();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        ((RefWatcher)ArmsUtils.obtainAppComponentFromContext(getApplicationContext()).extras()
                .get(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName()))).watch(this);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void settingDialog(String permissionMsg) {

    }

}
