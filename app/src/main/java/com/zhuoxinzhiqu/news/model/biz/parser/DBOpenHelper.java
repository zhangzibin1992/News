package com.zhuoxinzhiqu.news.model.biz.parser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 张梓彬 on 2016/10/26.
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    public DBOpenHelper(Context context) {
        super(context, "news", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news (_id integer primary key autoincrement,nid integer,title text,summary text,icon text,link text,type integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
