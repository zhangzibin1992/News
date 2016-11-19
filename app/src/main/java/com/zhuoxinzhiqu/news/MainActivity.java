package com.zhuoxinzhiqu.news;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Callback;

import com.zhuoxinzhiqu.news.adapter.NewsAdapter;
import com.zhuoxinzhiqu.news.common.LogUtil;
import com.zhuoxinzhiqu.news.common.MyActionBar;
import com.zhuoxinzhiqu.news.common.NewsTestCase;
import com.zhuoxinzhiqu.news.common.ParserData;
import com.zhuoxinzhiqu.news.common.ToastUtil;
import com.zhuoxinzhiqu.news.model.biz.parser.NewsDBManager;
import com.zhuoxinzhiqu.news.model.biz.parser.ParserNews;
import com.zhuoxinzhiqu.news.model.entity.News;
import com.zhuoxinzhiqu.news.model.entity.News2;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private MyActionBar myActionBar;
    private ListView listView;
    private NewsAdapter adapter;
    private NewsDBManager dbManger;
    private int limit = 10;
    private int offset;
    private boolean isLastRow = false;
    private  String res;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what==100){
                loadDataFromDB(limit,offset);
            }else if (what==200){
                ToastUtil.startToast(MainActivity.this,"网络连接错误");
            }
        }
    };

    private void loadDataFromDB(int limit2, int offset2){
        ArrayList<News> arraylist = dbManger.queryNews(limit2,offset2);
        adapter.addAllDataToAdapter(arraylist);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar);
        myActionBar.setActionBar("新闻",-1,-1,listen);
        findID();
         dbManger = NewsDBManager.getNewsDBManager(this);
        setAction();
    }

    private void findID() {
        listView = (ListView) findViewById(R.id.lv_news_listview);
    }

    private void setAction(){
        adapter = new NewsAdapter(this,listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemClickListener);
        listView.setOnScrollListener(sclistener);
        if (dbManger.getCount()>0){
            loadDataFromDB(limit,offset);
        }else{
            loading();

        }
    }

    private AbsListView.OnScrollListener sclistener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

            if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                int number = (int) dbManger.getCount();
                LogUtil.d(number+"");
                if (limit <number) {
                    limit += 10;
                    loadDataFromDB(limit, offset);
                    adapter.notifyDataSetChanged();
                }
                isLastRow = false;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                isLastRow = true;
            }
            /*if (totalItemCount>10&&listView.getLastVisiblePosition()+1==totalItemCount){
                loadDataFromDB(limit,offset);
            }*/
        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            News news = adapter.getAdapterData().get(position);
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("news",news);
            intent.putExtras(bundle);
            startActivity(intent);

        }
    };

    private View.OnClickListener listen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };




    private void loading(){
        new Thread(new Runnable() {
             String http = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
            @Override
            public void run() {
                try {
                    ParserNews parserNews = new ParserNews(MainActivity.this);
                    String string = NewsTestCase.getItems(http);
                    List<News> list =  ParserData.parserItemData(string);
                    for (int i = 0; i <list.size() ; i++) {

                        News news = list.get(i);
                        dbManger.insertNews(news);
                    }
                    handler.sendEmptyMessage(100);
                   /* JSONObject jsonObject = new JSONObject(string);

                    if (jsonObject.getString("message").equals("OK")){
                        ArrayList<News> arrayList =  parserNews.parser(jsonObject);
                        for (int i = 0; i <arrayList.size() ; i++) {

                            News news = arrayList.get(i);
                            dbManger.insertNews(news);
                        }
                        handler.sendEmptyMessage(100);
                    }else {
                        handler.sendEmptyMessage(200);
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(200);
                }



            }
        }).start();




    }
}
