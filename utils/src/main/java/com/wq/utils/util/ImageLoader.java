package com.wq.utils.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称：    com.wq.smartcampus.utils
 * 类描述
 * <pre>
 *     一个简易的图片异步加载工具,待完善
 * </pre>
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/9/27
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/9/27
 * 修改备注：
 */
public class ImageLoader {

    private ImageView mImageview;
    private String mUrl;
    //创建缓存
    private LruCache<String, Bitmap> mCaches;
    private ListView mListView;
    private Set<NewsAsyncTask> mTask;

    public ImageLoader() {
        mTask = new HashSet<>();
        //获得最大的缓存空间
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //赋予缓存区最大缓存的四分之一进行缓存
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }

    public ImageLoader(ListView listView) {
        mListView = listView;
        mTask = new HashSet<>();
        //获得最大的缓存空间
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //赋予缓存区最大缓存的四分之一进行缓存
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }

    //将图片通过url与bitmap的键值对形式添加到缓存中
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCaches.put(url, bitmap);
        }
    }

    //通过缓存得到图片
    public Bitmap getBitmapFromCache(String url) {
        return mCaches.get(url);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mImageview.getTag().equals(mUrl))
                mImageview.setImageBitmap((Bitmap) msg.obj);
        }
    };

    //通过线程的方式去展示图片
    public void showImageByThread(ImageView imageView, String url) {
        mImageview = imageView;
        mUrl = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromUrl(mUrl);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    /**
     * 异步请求加载图片
     *
     * @author ysc
     * @time 2018/9/27 16:07
     */
    public void showImageByAsync(ImageView imageView, String url) {
        //先从缓存中获取图片
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            NewAsyncTask task = new NewAsyncTask(imageView, url);
            task.execute(url);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    //通过异步任务的方式去加载图片

    public void showImageByAsyncTask(ImageView imageView, String url) {
        //先从缓存中获取图片
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
//            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //     private ImageView mImageView;
        private String mUrl;

        public NewsAsyncTask(String url) {
            //         mImageview = imageView;
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            //从网络获取图片
            Bitmap bitmap = getBitmapFromUrl(url);
            //将图片加入缓存中
            if (bitmap != null) {
                addBitmapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
        }
    }

    private class NewAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //     private ImageView mImageView;
        private String mUrl;
        private ImageView img;

        public NewAsyncTask(ImageView img, String url) {
            //         mImageview = imageView;
            mUrl = url;
            this.img = img;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            //从网络获取图片
            Bitmap bitmap = getBitmapFromUrl(url);
            //将图片加入缓存中
            if (bitmap != null) {
                addBitmapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (img != null && bitmap != null) {
                img.setImageBitmap(bitmap);
            }
        }
    }

    //滑动时加载图片
//    public void loadImages(int start, int end) {
//        for (int i = start; i < end; i++) {
//            String url = NewsAdapter.URLS[i];
//            //先从缓存中获取图片
//            Bitmap bitmap = getBitmapFromCache(url);
//            if (bitmap == null) {
//                NewsAsyncTask task = new NewsAsyncTask(url);
//                task.execute(url);
//                mTask.add(task);
//            } else {
//                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
//                imageView.setImageBitmap(bitmap);
//            }
//        }
//    }

    //停止时取消所有任务加载
    public void cancelAllTasks() {
        if (mTask != null) {
            for (NewsAsyncTask task : mTask) {
                task.cancel(false);
            }
        }
    }

    //网络获取图片
    private Bitmap getBitmapFromUrl(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}