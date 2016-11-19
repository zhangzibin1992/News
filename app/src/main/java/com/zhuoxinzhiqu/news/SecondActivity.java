package com.zhuoxinzhiqu.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zhuoxinzhiqu.news.base.MyBaseActivity;
import com.zhuoxinzhiqu.news.common.MyActionBar;
import com.zhuoxinzhiqu.news.model.entity.News;

public class SecondActivity extends MyBaseActivity {
    private MyActionBar myActionBar;
    private ProgressBar progressbar;
    private WebView webView;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findId();
        setAction();
    }

    private WebViewClient viewClinent = new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView webView,String url){
                webView.loadUrl(url);
                return true;
            }
    };

    private WebChromeClient wcclient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressbar.setProgress(newProgress);
            if (progressbar.getProgress()==100){
                progressbar.setVisibility(View.GONE);
            }

        }
    };



    private void findId(){
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar);
        progressbar = (ProgressBar) findViewById(R.id.pgb_loading);
        webView = (WebView) findViewById(R.id.webView);
    }

    private void setAction(){
        myActionBar.setActionBar("新闻",R.drawable.select_left,-1,listener);
        news = (News) getIntent().getSerializableExtra("news");
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(viewClinent);
        webView.setWebChromeClient(wcclient);
        webView.loadUrl(news.getLink());
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imv_left:
                    finish();
                    break;
            }
        }
    };
}
