package com.wq.students.base.integration;

/**
 * 项目名称：    com.wq.students.base.integration
 * 类描述        进度条下载回调
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/10/8
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/10/8
 * 修改备注：
 */
public interface IJsDownloadListener {

    void onStartDownload();

    void onProgress(int progress);

    void onFinishDownload();

    void onFail(String errorInfo);
}
