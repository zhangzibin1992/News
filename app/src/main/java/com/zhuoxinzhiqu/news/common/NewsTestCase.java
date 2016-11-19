package com.zhuoxinzhiqu.news.common;

import android.content.Context;
import android.util.Log;

import com.zhuoxinzhiqu.news.model.entity.News;

import junit.framework.TestCase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by 张梓彬 on 2016/10/24.
 */
public class NewsTestCase{
    private Context context;

    public NewsTestCase(Context context) {
        this.context = context;
    }

    public static String getItems(String path) throws Exception {
        String str = "";
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setReadTimeout(3000);
        con.setConnectTimeout(3000);

        int coid = con.getResponseCode();
        if (coid==200){
            InputStream is = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            int len = 0;
            byte[] buff = new byte[1024];
            while ((len=bis.read(buff))!=-1){
                str +=new String(buff,0,len);
            }
            bis.close();
            is.close();
            LogUtil.d(str+">>>>>>>>>>>>>>>>>>");
        }
        return str;


    }
}
