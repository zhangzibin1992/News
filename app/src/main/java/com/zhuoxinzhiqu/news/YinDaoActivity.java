package com.zhuoxinzhiqu.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuoxinzhiqu.news.adapter.MyPagerAdapter;

public class YinDaoActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private TextView tv_jump;
    private boolean isRunning;
    private ImageView[] imageViews = new ImageView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_dao);

        SharedPreferences preferences = getSharedPreferences("data",Context.MODE_PRIVATE);
        isRunning = preferences.getBoolean("context",true);
        if (!isRunning){
            isRunning = false;
            Intent intent = new Intent(YinDaoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }else {
            getAndSetView();
            setlayout();
        }

    }

    private void getAndSetView() {
        tv_jump = (TextView) findViewById(R.id.tv_jump);
        imageViews[0] = (ImageView) findViewById(R.id.dian_1);
        imageViews[1] = (ImageView) findViewById(R.id.dian_2);
        imageViews[2] = (ImageView) findViewById(R.id.dian_3);
        imageViews[3] = (ImageView) findViewById(R.id.dian_4);

        tv_jump.setVisibility(View.INVISIBLE);
        tv_jump.setOnClickListener(listener);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myPagerAdapter= new MyPagerAdapter(this);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnPageChangeListener(changeListener);
    }

    public void setlayout(){
        ImageView imageView = null;
        imageView= (ImageView) getLayoutInflater().inflate(R.layout.imagelayout,null);
        imageView.setImageResource(R.drawable.bd);
        myPagerAdapter.addViewToAdapter(imageView);

        imageView= (ImageView) getLayoutInflater().inflate(R.layout.imagelayout,null);
        imageView.setImageResource(R.drawable.small);
        myPagerAdapter.addViewToAdapter(imageView);

        imageView= (ImageView) getLayoutInflater().inflate(R.layout.imagelayout,null);
        imageView.setImageResource(R.drawable.wy);
        myPagerAdapter.addViewToAdapter(imageView);

        imageView= (ImageView) getLayoutInflater().inflate(R.layout.imagelayout,null);
        imageView.setImageResource(R.drawable.welcome);
        myPagerAdapter.addViewToAdapter(imageView);

        myPagerAdapter.notifyDataSetChanged();

    }

    private ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tv_jump.setVisibility(View.INVISIBLE);
            if (position==3){
                tv_jump.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i <imageViews.length ; i++) {
                imageViews[i].setImageResource(R.drawable.adware_style_default);
            }
            imageViews[position].setImageResource(R.drawable.adware_style_selected);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public void secondRun(){
        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("context",false);
        editor.commit();
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            secondRun();
            Intent intent = new Intent(YinDaoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
