package com.wq.students.base.utils.netApi;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 项目名称：    com.wq.students.base.utils.netApi
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/9
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/9
 * 修改备注：
 */
public interface HttpApi {

    /**
     *
     * 检测版本更新
     * @param loginType         当前设备类型
     * @param versionCode       版本code
     * @author ysc
     * @time 2018/10/8 15:04
     */
    @GET("/school/api/user_version")
    Observable<ResponseBody> detectionVersion(@Query("versionCode") String versionCode, @Query("loginType") String loginType);

    /**
     * 下载
     * @param url       下载地址
     * @author ysc
     * @time 2018/10/8 15:19
     */
    @GET
    Observable<ResponseBody> download(@Url String url);
}