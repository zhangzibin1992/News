package com.zhuoxinzhiqu.news.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhuoxinzhiqu.news.common.LogUtil;

/**
 * @author zhangzibin
 * Created by zhangzibin on 2016/10/19.
 */
public class MyBaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected int screen_w,screen_h;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        screen_w=getWindowManager().getDefaultDisplay().getWidth();
        screen_h=getWindowManager().getDefaultDisplay().getHeight();
        LogUtil.d(getClass().getSimpleName()+"onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(getClass().getSimpleName()+"onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(getClass().getSimpleName()+"onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(getClass().getSimpleName()+"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(getClass().getSimpleName()+"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(getClass().getSimpleName()+"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(getClass().getSimpleName()+"onDestroy()");
    }

    /**页面跳转*/
    public void jumpActivity(Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivity(intent);
    }

    /**携带数据跳转*/
    public void jumpActivityWithData(Class<?> cls,Bundle bundle){
        Intent intent = new Intent(this,cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**携带Bundle，Uri数据跳转*/
    public void jumpActivityWithDataAndUri(Class<?> cls, Bundle bundle, Uri uri){
        Intent intent = new Intent(this,cls);
        if(bundle ==null ){
            intent.putExtras(bundle);
        }
        if (uri!=null){
            intent.setData(uri);
        }
        startActivity(intent);
    }

    /**带动画跳转页面*/
    public void jumpActivityWithAnim(Class<?> cls,int SecondAnim,int FirstAnim ){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivity(intent);
        overridePendingTransition(SecondAnim,FirstAnim);
    }


    /**设置监听，子类需重写该方法*/
    public void onClick(View v){

    }





}


