package com.zhuoxinzhiqu.news.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张梓彬 on 2016/10/26.
 */
public class LoadImage {

    private static Map<String,SoftReference<Bitmap>> softReferences = new HashMap<String,SoftReference<Bitmap>>();



    private Context context;
    private ImageLoadListener listener;

    public LoadImage(Context context, ImageLoadListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public interface ImageLoadListener{
        void imageLoadOk(Bitmap bitmap, String url);
    }


    public void saveCachFile(String url,Bitmap bitmap){
        //http://aa/t.jpg  获取文件名字
        String name=url.substring(url.lastIndexOf("/")+1);
        //返回的路径目录应用程序缓存文件
        File cacheDir=context.getCacheDir();
        if(!cacheDir.exists()){
            cacheDir.mkdir();
        }
        //建立输出流
        OutputStream outStream=null;
        try {
            outStream=new FileOutputStream(new File(cacheDir,name));
            //存图片到文件
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(outStream!=null){
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存图片到软引用缓存中
     * @param url  图片的原路径 网络 http://aa/t.jpg
     * @param bitmap   来自文件的图片
     */
    public void saveSoftReferences(String url,Bitmap bitmap){
        //存在内存中 一个图片
        SoftReference<Bitmap> softbitmap=new SoftReference<Bitmap>(bitmap);
        //存在集合中
        softReferences.put(url, softbitmap);
    }

    /**
     * 得到内存的图片 软引用
     * @param url 图片路径
     * @return bitmap 图片
     */
    public Bitmap getBitmapSoftReferences(String url){
        Bitmap bitmap=null;
        //如果内存集合中根据key 得到values
        if(softReferences.containsKey(url)){
            //得到软引用中的图片
            bitmap=softReferences.get(url).get();
        }
        return bitmap;
    }

    /**
     * 从缓存文件中获取图片
     * @param url 图片路径  com.app.azy.news.cache/xxx.png
     * @return  缓存文件中的图片
     */
    private Bitmap getBitmapFromCache(String url){
        String name=url.substring(url.lastIndexOf("/")+1);
        //获取当前包下的缓存文件路径
        File cacheDir=context.getCacheDir();
        //得到该文件夹下所有文件
        File[] files=cacheDir.listFiles();
        if(files==null){
            return null;
        }
        //图片文件
        File bitFile=null;
        //如有名字和传入的文件名一致的则找到图片
        for (File file : files) {
            if(file.getName().equals(name)){
                bitFile=file;
                break;}
        }
        //如果没有找到，返回空
        if(bitFile==null){
            return null;
        }
        /**
         * 把找的文件 转换为bitmap
         */
        Bitmap bitmap=BitmapFactory.decodeFile(bitFile.getAbsolutePath());
        return bitmap;
    }


    /**
     * 异步加载网络图片
     * @param url 图片在网络中的路径
     *
     */
    private void getBitmapAsync(String url){
        //自定义的异步加载类
        ImageAsyncTask imageAsyncTask=new ImageAsyncTask();
        //执行加载
        imageAsyncTask.execute(url);


    }


    public class ImageAsyncTask extends AsyncTask<String,Void,Bitmap>{
        private String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            url = params[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream is = con.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                saveSoftReferences(params[0],bitmap);
                saveCachFile(params[0],bitmap);
                is.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (listener==null){
                listener.imageLoadOk(bitmap,url);
            }
        }
    }

    /**
     * 最终调用的方法:先查看内存中有没有，再看缓存文件中 有没有，最后只能向网络请求图片
     * @param url 图片路径
     * @return  图片
     */
    public  Bitmap getBitmap(String url){
        Bitmap bitmap=null;
        if(url==null || url.length()<=0){
            return bitmap;
        }
        //先看内存中
        bitmap=getBitmapSoftReferences(url);
        if(bitmap!=null){
            return bitmap;
        }
        //是不是缓存文件的
        bitmap=getBitmapFromCache(url);
        if(bitmap!=null){
            return bitmap;
        }
        getBitmapAsync(url);

        return bitmap;
    }


}
