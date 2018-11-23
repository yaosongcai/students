package com.wq.utils.http;

import android.content.ContentResolver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.wq.utils.imagetrans.ImageLoad;
import com.wq.utils.imagetrans.TileBitmapDrawable;

import java.util.HashMap;
import java.util.regex.Pattern;



/**
 * Created by liuting on 17/6/1.
 */

public class MyImageLoad implements ImageLoad {
    private static final Pattern webPattern = Pattern.compile("http[s]*://[[[^/:]&&[a-zA-Z_0-9]]\\.]+(:\\d+)?(/[a-zA-Z_0-9]+)*(/[a-zA-Z_0-9]*([a-zA-Z_0-9]+\\.[a-zA-Z_0-9]+)*)?(\\?(&?[a-zA-Z_0-9]+=[%[a-zA-Z_0-9]-]*)*)*(#[[a-zA-Z_0-9]|-]+)?(.jpg|.png|.gif|.jpeg)?");
    private static final String ASSET_PATH_SEGMENT = "android_asset";
    private static final HashMap<String, LoadCallback> loadCallbackMap = new HashMap<>();

    @Override
    public void loadImage(final String url, final LoadCallback callback, final ImageView imageView, final String unique) {
        addLoadCallback(unique, callback);
        loadImageFromLocal(url, unique, imageView);
//        Uri uri = Uri.parse(url);
//        if (isLocalUri(uri.getScheme())) {
//            if (isAssetUri(uri)) {
//                //是asset资源文件
//
//                return;
//            } else {
//                //是本地文件
//                loadImageFromLocal(uri.getPath(), unique, imageView);
//                return;
//            }
//        } else {
//            Log.e("MyImageLoad", "未知的图片URL的类型");
//        }
    }

    /**
     * 从本地加载图片
     */
    private void loadImageFromLocal(String url, final String unique, final ImageView imageView) {
        TileBitmapDrawable.attachTileBitmapDrawable(imageView, url, new TileBitmapDrawable.OnLoadListener() {
            @Override
            public void onLoadFinish(TileBitmapDrawable drawable) {
                onFinishLoad(unique, drawable);
            }

            @Override
            public void onError(Exception ex) {

            }
        });
    }

    public static void addLoadCallback(String unique, LoadCallback callback) {
        loadCallbackMap.put(unique, callback);
    }

    public static void removeLoadCallback(String unique) {
        loadCallbackMap.remove(unique);
    }

    public static void onFinishLoad(String unique, Drawable drawable) {
        LoadCallback loadCallback = loadCallbackMap.remove(unique);
        if (loadCallback != null) {
            loadCallback.loadFinish(drawable);
        }
    }

    public static void onProgress(String unique, float progress) {
        LoadCallback loadCallback = loadCallbackMap.get(unique);
        if (loadCallback != null) {
            loadCallback.progress(progress);
        }
    }


    @Override
    public boolean isCached(String url) {
//        if (isLocalUri(Uri.parse(url).getScheme())) {
            //是本地图片不用预览图
            return true;
//        }
    }

    @Override
    public void cancel(String url, String unique) {
        removeLoadCallback(unique);
    }

    private static boolean isNetUri(String url) {
        return webPattern.matcher(url).find();
    }

    private static boolean isLocalUri(String scheme) {
        return ContentResolver.SCHEME_FILE.equals(scheme)
                || ContentResolver.SCHEME_CONTENT.equals(scheme)
                || ContentResolver.SCHEME_ANDROID_RESOURCE.equals(scheme);
    }

    public static boolean isAssetUri(Uri uri) {
        return ContentResolver.SCHEME_FILE.equals(uri.getScheme()) && !uri.getPathSegments().isEmpty()
                && ASSET_PATH_SEGMENT.equals(uri.getPathSegments().get(0));
    }

}
