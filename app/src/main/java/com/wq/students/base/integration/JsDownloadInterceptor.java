package com.wq.students.base.integration;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 项目名称：    com.wq.students.base.integration
 * 类描述        带进度下载  拦截器
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/8
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/8
 * 修改备注：
 */
public class JsDownloadInterceptor implements Interceptor{

    private IJsDownloadListener downloadListener;

    public JsDownloadInterceptor(IJsDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new JsResponseBody(response.body(), downloadListener)).build();
    }
}
