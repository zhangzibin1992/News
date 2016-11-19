package com.zhuoxinzhiqu.news.control;

import android.app.Application;
import android.content.res.Configuration;

import com.zhuoxinzhiqu.news.common.LogUtil;

import java.util.HashMap;

/**
 * @author zhangzibin
 * Created by Zhangzibin on 2016/10/19.
 */
public class MyApplication extends Application {

    /**用来保存整个应用程序的数据*/
    private HashMap<String,Object> hashMap = new HashMap<String, Object>();

    /**存数据 */
    public void addAllData(String key,Object value){
        hashMap.put(key,value);
    }

    /**取数据*/
    public Object getAllData(String key){
        if(hashMap.containsKey(key)){
            return hashMap.get(key);
        }
        return null;
    }


    /**删除一条数据*/
    public void delOneData(String key){
        if (hashMap.containsKey(key)){
             hashMap.remove(key);
        }
    }


    /**删除所有数据*/
    public void delAllData(){
        hashMap.clear();
    }

    /**单例模式*/
    public static MyApplication myApplication;

    public static MyApplication getInstance(){
        LogUtil.d("MyApplication onCreate");
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        LogUtil.d("myApplication onCreate");
    }

    /**内存不足的时候*/
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtil.d("myApplication onLowMemory");
    }

    /** * 结束的时候 */
    @Override
    public void onTerminate() {
        super.onTerminate();
        LogUtil.d("myApplication onTerminate");
    }

    /**配置改变的时候*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.d("myApplication onConfigurationChanged");
    }
}
