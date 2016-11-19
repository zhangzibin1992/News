package com.zhuoxinzhiqu.news.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuoxinzhiqu.news.model.entity.News;
import com.zhuoxinzhiqu.news.model.entity.News2;
import com.zhuoxinzhiqu.news.model.entity.One;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23.
 */
public class ParserData {

    //Gson解析
    public static List<News> parserItemData(String json){

        Gson gson = new Gson();
        One result = gson.fromJson(json, new TypeToken<One>() {
        }.getType());

        return result.getData();

    }

}
