package com.zhuoxinzhiqu.news.model.entity;

import java.util.List;

/**
 * Created by 张梓彬 on 2016/10/28.
 */
public class One {
    private String message;
    private List<News> data;

    public One(String message, List<News> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }
}
