/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wq.students.base.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.google.gson.GsonBuilder;
import com.wq.students.BuildConfig;
import com.wq.students.base.base.delegate.AppDelegate;
import com.wq.students.base.base.delegate.AppLifecycles;
import com.wq.students.base.di.module.AppModule;
import com.wq.students.base.di.module.ClientModule;
import com.wq.students.base.di.module.GlobalConfigModule;
import com.wq.students.base.integration.ConfigModule;
import com.wq.students.base.integration.ManifestParser;
import com.wq.students.base.integration.log.FormatPrinter;
import com.wq.students.base.integration.log.RequestInterceptor;
import com.wq.students.base.utils.Timber;
import com.wq.students.base.utils.netApi.Api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache2.internal.RxCache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ================================================
 * App 的全局配置信息在此配置, 需要将此实现类声明到 AndroidManifest 中
 * ConfigModule 的实现类可以有无数多个, 在 Application 中只是注册回调, 并不会影响性能 (多个 ConfigModule 在多 Module 环境下尤为受用)
 * 不过要注意 ConfigModule 接口的实现类对象是通过反射生成的, 这里会有些性能损耗
 *
 * @see AppDelegate
 * @see ManifestParser
 * ================================================
 */
public final class GlobalConfiguration implements ConfigModule {
//    public static String sDomain = Api.APP_DOMAIN;

    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        if (!BuildConfig.DEBUG) { //Release 时,让框架不再打印 Http 请求和响应的信息
            builder.printHttpLogLevel(RequestInterceptor.Level.NONE);
        }

        builder.baseurl(Api.APP_DOMAIN)
                //强烈建议自己自定义图片加载逻辑, 因为 arms-imageloader-glide 提供的 GlideImageLoaderStrategy 并不能满足复杂的需求
                //请参考 https://github.com/JessYanCoding/MVPArms/wiki#3.4
//                .imageLoaderStrategy(new GlideImageLoaderStrategy())

                //想支持多 BaseUrl, 以及运行时动态切换任意一个 BaseUrl, 请使用 https://github.com/JessYanCoding/RetrofitUrlManager
                //如果 BaseUrl 在 App 启动时不能确定, 需要请求服务器接口动态获取, 请使用以下代码
                //以下方式是 Arms 框架自带的切换 BaseUrl 的方式, 在整个 App 生命周期内只能切换一次, 若需要无限次的切换 BaseUrl, 以及各种复杂的应用场景还是需要使用 RetrofitUrlManager 框架
                //以下代码只是配置, 还要使用 Okhttp (AppComponent中提供) 请求服务器获取到正确的 BaseUrl 后赋值给 GlobalConfiguration.sDomain
                //切记整个过程必须在第一次调用 Retrofit 接口之前完成, 如果已经调用过 Retrofit 接口, 此种方式将不能切换 BaseUrl
//                .baseurl(new BaseUrl() {
//                    @Override
//                    public HttpUrl url() {
//                        return HttpUrl.parse(sDomain);
//                    }
//                })

                //可根据当前项目的情况以及环境为框架某些部件提供自定义的缓存策略, 具有强大的扩展性
//                .cacheFactory(new Cache.Factory() {
//                    @NonNull
//                    @Override
//                    public Cache build(CacheType type) {
//                        switch (type.getCacheTypeId()){
//                            case CacheType.EXTRAS_TYPE_ID:
//                                return new IntelligentCache(500);
//                            case CacheType.CACHE_SERVICE_CACHE_TYPE_ID:
//                                return new Cache(type.calculateCacheSize(context));//自定义 Cache
//                            default:
//                                return new LruCache(200);
//                        }
//                    }
//                })

                //若觉得框架默认的打印格式并不能满足自己的需求, 可自行扩展自己理想的打印格式 (以下只是简单实现)
                .formatPrinter(new FormatPrinter() {
                    @Override
                    public void printJsonRequest(Request request, String bodyString) {
                        Timber.i("printJsonRequest:" + bodyString);
                    }

                    @Override
                    public void printFileRequest(Request request) {
                        Timber.i("printFileRequest:" + request.url().toString());
                    }

                    @Override
                    public void printJsonResponse(long chainMs, boolean isSuccessful, int code,
                                                  String headers, MediaType contentType, String bodyString,
                                                  List<String> segments, String message, String responseUrl) {
                        Timber.i("printJsonResponse:" + bodyString);
                    }

                    @Override
                    public void printFileResponse(long chainMs, boolean isSuccessful, int code, String headers,
                                                  List<String> segments, String message, String responseUrl) {
                        Timber.i("printFileResponse:" + responseUrl);
                    }
                })

                // 这里提供一个全局处理 Http 请求和响应结果的处理类,可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
                .globalHttpHandler(new GlobalHttpHandlerImpl(context))
                // 用来处理 rxjava 中发生的所有错误,rxjava 中发生的每个错误都会回调此接口
                // rxjava必要要使用ErrorHandleSubscriber(默认实现Subscriber的onError方法),此监听才生效
                .responseErrorListener(new ResponseErrorListenerImpl())
                .gsonConfiguration(new AppModule.GsonConfiguration() {
                    @Override
                    public void configGson(Context context, GsonBuilder builder) {
                        builder
                                .serializeNulls()//支持序列化null的参数
                                .enableComplexMapKeySerialization();//支持将序列化key为object的map,默认只能序列化key为string的map
                    }
                })
                .retrofitConfiguration(new ClientModule.RetrofitConfiguration() {
                    @Override
                    public void configRetrofit(Context context, Retrofit.Builder builder) {
                        //这里可以自己自定义配置Retrofit的参数, 甚至您可以替换框架配置好的 OkHttpClient 对象 (但是不建议这样做, 这样做您将损失框架提供的很多功能)
                        builder.addConverterFactory(GsonConverterFactory.create());//比如使用fastjson替代gson
                    }
                })
                .okhttpConfiguration(new ClientModule.OkhttpConfiguration() {
                    @Override
                    public void configOkhttp(Context context, OkHttpClient.Builder builder) {
                        //这里可以自己自定义配置Okhttp的参数
//                        builder.sslSocketFactory(); //支持 Https,详情请百度
                        builder.writeTimeout(10, TimeUnit.SECONDS);
                        //使用一行代码监听 Retrofit／Okhttp 上传下载进度监听,以及 Glide 加载进度监听 详细使用方法查看 https://github.com/JessYanCoding/ProgressManager
//                        ProgressManager.getInstance().with(okhttpBuilder);
                        //让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl. 详细使用请方法查看 https://github.com/JessYanCoding/RetrofitUrlManager
//                        RetrofitUrlManager.getInstance().with(okhttpBuilder);
                    }
                })
                .rxCacheConfiguration(new ClientModule.RxCacheConfiguration() {
                    @Override
                    public RxCache configRxCache(Context context, RxCache.Builder builder) {
                        //这里可以自己自定义配置 RxCache 的参数
                        builder.useExpiredDataIfLoaderNotAvailable(true);
                        // 想自定义 RxCache 的缓存文件夹或者解析方式, 如改成 fastjson, 请 return rxCacheBuilder.persistence(cacheDirectory, new FastJsonSpeaker());
                        // 否则请 return null;
                        return null;
                    }
                });
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        // AppLifecycles 的所有方法都会在基类 Application 的对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        // 可以根据不同的逻辑添加多个实现类
        lifecycles.add(new AppLifecyclesImpl());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        // ActivityLifecycleCallbacks 的所有方法都会在 Activity (包括三方库) 的对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        // 可以根据不同的逻辑添加多个实现类
        lifecycles.add(new ActivityLifecycleCallbacksImpl());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        lifecycles.add(new FragmentLifecycleCallbacksImpl());
    }

}
