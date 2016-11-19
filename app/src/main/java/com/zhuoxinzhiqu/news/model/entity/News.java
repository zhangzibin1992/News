package com.zhuoxinzhiqu.news.model.entity;

import java.io.Serializable;

/**
 * Created by 张梓彬 on 2016/10/24.
 */
public class News implements Serializable {

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

    private String summary;
    private String icon;
   // private String stamp;
    private String title;
    private int nid;
    private String link;
    private int type;

    public News(String summary, String icon,  String title, int nid, String link, int type) {
        this.summary = summary;
        this.icon = icon;
       // this.stamp = stamp;
        this.title = title;
        this.nid = nid;
        this.link = link;
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

   /* public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "News{" +
                "summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", nid=" + nid +
                ", link='" + link + '\'' +
                ", type=" + type +
                '}';
    }
}
