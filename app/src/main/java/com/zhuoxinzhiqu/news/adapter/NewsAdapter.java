package com.zhuoxinzhiqu.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhuoxinzhiqu.news.R;
import com.zhuoxinzhiqu.news.common.CommonUtil;
import com.zhuoxinzhiqu.news.common.LoadImage;
import com.zhuoxinzhiqu.news.model.entity.News;

/**
 * Created by 张梓彬 on 2016/10/26.
 */
public class NewsAdapter extends MyBaseAdapter<News> {
    private LoadImage.ImageLoadListener listener = new LoadImage.ImageLoadListener() {
        @Override
        public void imageLoadOk(Bitmap bitmap, String url) {
            ImageView igv_list = (ImageView) listView.findViewWithTag(url);
            if (igv_list==null){
                igv_list.setImageBitmap(bitmap);
            }
        }
    };

    private Bitmap defaultBitmap;
    private ListView listView;
    private LoadImage loadImage;

    /**
     * 实例化布局填充器
     *
     * @param context
     */
    public NewsAdapter(Context context,ListView lv) {
        super(context);
        defaultBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.defaultpic);
        listView = lv;
        loadImage = new LoadImage(context,listener);
    }





    public View getView(int position, View convertView, ViewGroup parent) {

        HoldView holdView = null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.list_layout,null);
            holdView = new HoldView(convertView);
            convertView.setTag(holdView);
        }else{
            holdView = (HoldView) convertView.getTag();
        }

        News news = arrayList.get(position);
        holdView.tv_list_title.setText(news.getTitle());
        holdView.tv_list_context.setText(news.getSummary());
        holdView.igv_list.setImageBitmap(defaultBitmap);

        String uriImage=news.getIcon();
        holdView.igv_list.setTag(CommonUtil.NETPATH+uriImage);
        Bitmap bitmap = loadImage.getBitmap(uriImage);
        if (bitmap!=null){
            holdView.igv_list.setImageBitmap(bitmap);
        }
        return convertView;
    }

    public class HoldView{
        public ImageView igv_list;
        public TextView tv_list_title,tv_list_context;

        public HoldView(View view){
            igv_list = (ImageView) view.findViewById(R.id.igv_list);
            tv_list_title = (TextView) view.findViewById(R.id.tv_list_title);
            tv_list_context = (TextView) view.findViewById(R.id.tv_list_context);
        }
    }
}
