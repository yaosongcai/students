package com.wq.students.reward.model.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * 项目名称：    com.wq.students.reward.model.api
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/21
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/21
 * 修改备注：
 */
public interface RewardApi {

    /**
     * 获取学生历史奖罚记录
     * @return
     * @author ysc
     * @time 2018/11/21 9:52
     */
    @GET("/school/api/examPewardPun_queryByPage")
    Observable<ResponseBody> queryRewardList();

    /**
     *
     * 获取学生最新奖罚信息
     * @return
     * @author ysc
     * @time 2018/11/21 9:54
     */
    @GET("/school/api/examPewardPun_queryByAgoData")
    Observable<ResponseBody> queryRewardNewList();
}
