package com.zhuoxinzhiqu.news.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzibin
 * Created by zhangzibin on 2016/10/19.
 */
public class MyBaseAdapter<T> extends BaseAdapter{
    private Context context;
    protected LayoutInflater inflater;

    /**实例化布局填充器*/
    public MyBaseAdapter(Context context) {
        super();
        this.context=context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    protected List<T> list = new ArrayList<T>();

    /**清空List集合*/
    public void clearList(){
        list.clear();
    }

    protected ArrayList<T> arrayList = new ArrayList<T>();

    public List<T> getAdapterData(){
        return arrayList;
    }

    /**添加实体到Adapter*/
    public void addDataToAdapter(T t){
        arrayList.add(t);
    }

    /**添加一个集合到Adapter*/
    public void addAllDataToAdapter(List<T> list){
        arrayList.clear();
        arrayList.addAll(list);
    }

    /**删除集合最后一项*/
    public void removeArrayList(){
        arrayList.remove(arrayList.size()-1);
    }

    /**清空ArrayList集合*/
    public void clearArrayList(){
        arrayList.clear();
    }


    /**返回列表个数*/
    @Override
    public int getCount() {
        return arrayList.size();
    }


    /**获取列表项角标*/
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    /**获取列表各项ID*/
    @Override
    public long getItemId(int position) {
        return position;
    }


    /**子类需要重写getView方法，在子类中添加数据*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
