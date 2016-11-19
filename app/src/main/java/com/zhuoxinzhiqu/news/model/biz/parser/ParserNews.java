package com.zhuoxinzhiqu.news.model.biz.parser;

import android.content.Context;
import android.util.Log;

import com.zhuoxinzhiqu.news.common.LogUtil;
import com.zhuoxinzhiqu.news.model.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 张梓彬 on 2016/10/24.
 */
public class ParserNews {

    /* "message": "OK",
            "status": 0,
            "data": [
        {
            "summary": "英超将于本月15号重燃战火，前七轮最火的球员是谁？非孙兴民莫属。热刺本赛季至今也以5胜2平的不败战绩排名英超第二，仅次于曼城。在热刺2：1击败米德尔斯堡的比赛中，孙兴民独中两元，帮助球队在客场全取三分。在欧冠小组赛第二轮的比赛中，也是孙兴民的进球帮助球队1：0取得胜利。 \t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t\t\t\t",
                "icon": "http://118.244.212.82:9092/Images/20161009031226.jpg",
                "stamp": "2016-10-09 08:21:45.0",
                "title": "英超球员场均得分排行榜，韩国一哥优势明显",
                "nid": 44,
                "link": "http://mini.eastday.com/a/161009082150384.html",
                "type": 1
        }*/
    private Context context;


    public ParserNews(Context context) {
        this.context = context;
    }

    public ArrayList<News> parser(JSONObject jsonObject) throws Exception {
        ArrayList<News> arrayList = new ArrayList<News>();

        JSONArray jsonArray = jsonObject.getJSONArray("data");

        for (int i = 0; i <jsonArray.length() ; i++) {
            JSONObject object = jsonArray.getJSONObject(i);

            String summary = object.getString("summary");
            String icon = object.getString("icon");
           // String stamp = object.getString("stamp");
            String title = object.getString("title");
            int nid = object.getInt("nid");
            String link = object.getString("link");
            int type = object.getInt("type");

            News news = new News(summary,icon,title,nid,link,type);
            arrayList.add(news);
            Log.d("DDD",title+"111111111111111111111111111");
        }
        return arrayList;


    }
}
