package com.wq.students.timetable.model.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 项目名称：    com.wq.students.timetable.model.api
 * 类描述        课表API
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/11/19
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/11/19
 * 修改备注：
 */
public interface TimetableApi {

    /**
     *
     * 查询学期
     * @author ysc
     * @time 2018/11/19 11:57
     */
    @POST("/school/api/menu_queryTermList")
    Observable<ResponseBody> queryTermList();

    /**
     *
     * 查询班级
     * @author ysc
     * @time 2018/11/19 11:57
     */
    @POST("/school/api/menu_queryClassList")
    Observable<ResponseBody> queryClassList();

    /**
     *
     * 查询课表
     * @param termid    学期id
     * @param classid   班级id
     * @author ysc
     * @time 2018/11/19 11:54
     */
    @GET("/school/api/menu_queryStCourseList")
    Observable<ResponseBody> queryStCoruseLists(@Query("termid")String termid, @Query("classid")String classid);
}
