package com.example.administrator.giftelves.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.giftelves.activitys.HitThirdActivity;
import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.SpecialHitThird;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;

import java.util.List;

/**
 * 暴打星期三的适配器
 * Created by Administrator on 2016/12/30.
 */

public class SpecialHitThirdAdapter extends BaseAdapter {
    //有对象的集合
    private List<SpecialHitThird.ListBean> mlist;
    //有上下文
    private Context mcontext;
    //有LayoutInflater
    private LayoutInflater inflate;
    //    private NewsSQL mysql;
    //公有构造方法
    public SpecialHitThirdAdapter(Context context,List<SpecialHitThird.ListBean> list){
        this.mcontext = context;
        this.mlist = list;
//        mysql = new NewsSQL(context);
        inflate = LayoutInflater.from(context);
    }
//	private News getNews(){
//		SQLiteDatabase db = mysql.getReadableDatabase();
//		String sql = "select news_name,news_author,news_iconurl from "+NewsSQL.NEWS_TB+" where news_id = ?";
//		News news  =new News();
//		Cursor cursor = db.rawQuery(sql,new String[]{news.getId()});
//		while(cursor.moveToNext()){
//			news.setName(cursor.getString(cursor.getColumnIndex("news_name")));
//			news.setAutorImage(cursor.getString(cursor.getColumnIndex("news_author")));
//			news.setIconurl(cursor.getString(cursor.getColumnIndex("news_iconurl")));
//		}
//		cursor.close();
//		db.close();
//		return news;

    //	}
    @Override
    public int getCount() {

        return null==mlist?0:mlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return mlist.get(position).getSid();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if(null==view){
            view = inflate.inflate(R.layout.special_hit_third_item,parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final SpecialHitThird.ListBean listBean= mlist.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, HitThirdActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("sid",listBean.getSid());
                bundle.putString("game",listBean.getName());
                bundle.putString("back",listBean.getIconurl());
                bundle.putString("addtime",listBean.getAddtime());
                bundle.putString("desc",listBean.getDescs());
                intent.putExtra("hit",bundle);
                mcontext.startActivity(intent);
            }
        });
        viewHolder.newsName.setText(listBean.getName());
        viewHolder.addTime.setText(listBean.getAddtime());
        viewHolder.backImage.setImageResource(R.mipmap.def_loading);
        String urling = Constants.COMMON_URL+listBean.getIconurl();
        viewHolder.backImage.setTag(urling);
        ImageLoader.loadImage(urling,400,250,new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String url) {
                if(url.equals(viewHolder.backImage.getTag())){
                    viewHolder.backImage.setImageBitmap(bitMap);
                }
            }
        });

        return view;
    }
    class ViewHolder{
        ImageView backImage;
        TextView newsName;
        TextView addTime;
        public ViewHolder(View view){
            backImage = (ImageView)view.findViewById(R.id.hit_third_image);
            newsName =(TextView)view.findViewById(R.id.hit_third_news);
            addTime = (TextView) view.findViewById(R.id.hit_third_addtime);
            view.setTag(this);
        }
    }
}

