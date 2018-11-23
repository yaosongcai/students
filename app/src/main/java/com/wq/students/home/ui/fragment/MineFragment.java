package com.wq.students.home.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wq.students.R;
import com.wq.students.base.base.BaseFragment;
import com.wq.students.base.di.component.AppComponent;
import com.wq.utils.util.ScreenUtils;
import com.wq.utils.view.widget.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 项目名称：
 * 类描述     我的页面
 * 创建人：   Yaosongcai
 * 创建时间： 2018\5\9 0009 16:14
 * 修改人：   Yaosongcai
 * 修改时间： Yaosongcai or 2018\5\9 0009 16:14
 * 修改备注：
 */

public class MineFragment extends BaseFragment  {

    private OnFragmentInteractionListener mListener;
    private View mView;

    private String[] mPersion = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private static final int REQUEST_READ = 60;

    @BindView(R.id.ll_personal_information)
    LinearLayout ll_personal_information;
    @BindView(R.id.rel_changepd)
    RelativeLayout rel_set;//设置
    @BindView(R.id.riv_icon)
    RoundImageView riv_icon;
    @BindView(R.id.tv_name)
    TextView tv_name;
//    @BindView(R.id.tv_phone)
//    TextView tv_phone;
//    @BindView(R.id.tv_company)
//    TextView tv_company;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick({R.id.riv_icon, R.id.ll_personal_information, R.id.rel_changepd, R.id.rel_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.riv_icon:
                //更改头像
//                startActivity(new Intent(getContext(), ChatHeadActivity.class));
                break;
            case R.id.ll_personal_information:
                //个人信息详情页
//                startActivity(new Intent(getContext(), PersonalInformationActivity.class));
                break;
            case R.id.rel_changepd:
                //更改密码
//                startActivity(new Intent(getContext(), ChangePDActivity.class));
                break;
            case R.id.rel_update:
                if (ContextCompat.checkSelfPermission(getContext(),mPersion[0]) != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),mPersion[0])){
                        settingDialog("读取文件");
                    } else {
                        ActivityCompat.requestPermissions(getActivity(),mPersion,REQUEST_READ);
                    }
                    return;
                }
                //检查更新
//                mPersenter.CheckVersion(null);
                break;
        }
    }

    private void settingDialog(String permissionMsg){
//        CarVerifyDialog dialog = new CarVerifyDialog.Builder(getContext())
//                .setTitle("提示")
//                .setMessage("无法获取"+permissionMsg+"权限，请先开启此权限", Color.rgb(51,51,51))
//                .setLeftColor(Color.rgb(51,51,51))
//                .setRightColor(Color.rgb(0, 120, 255))
//                .setLeftButton("取消", new CarVerifyDialog.OnLeftClick() {
//                    @Override
//                    public void onClick() {
//
//                    }
//                })
//                .setRightButton("去设置", new CarVerifyDialog.OnRightClick() {
//                    @Override
//                    public void onClick() {
//                        SettingUtils.getInstance(getContext()).gotoSetting();
//                    }
//                })
//                .create();
//        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_READ:
                if (ContextCompat.checkSelfPermission(getContext(),permissions[0]) != PackageManager.PERMISSION_GRANTED){
                    settingDialog("读取文件");
                    return;
                }
//                mPersenter.CheckVersion(null);
                break;
        }
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, container, false);
        return mView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            mView.findViewById(R.id.view_status).getLayoutParams().height = ScreenUtils.getStatusHeight();
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
