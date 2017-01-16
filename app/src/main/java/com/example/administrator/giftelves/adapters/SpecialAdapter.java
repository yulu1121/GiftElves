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

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.activitys.SpecialNewsActivity;
import com.example.administrator.giftelves.enties.SpecialNews;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;

import java.util.List;

/**
 * 特色适配器
 * Created by Administrator on 2016/12/28.
 */

public class SpecialAdapter extends BaseAdapter{
    //有对象的集合
    private List<SpecialNews.ListBean> mlist;
    //有上下文
    private Context mcontext;
    //有LayoutInflater
    private LayoutInflater inflate;
//    private NewsSQL mysql;
    //公有构造方法
    public SpecialAdapter(Context context,List<SpecialNews.ListBean> list){
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
        return mlist.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if(null==view){
            view = inflate.inflate(R.layout.special_item_magzine,parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        final SpecialNews.ListBean listBean= mlist.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, SpecialNewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",listBean.getId());
                bundle.putString("name",listBean.getName());
                bundle.putString("back",listBean.getIconurl());
                bundle.putString("author",listBean.getAuthorimg());
                bundle.putString("desc",listBean.getDescs());
                intent.putExtra("news",bundle);
                mcontext.startActivity(intent);
            }
        });
        viewHolder.newsName.setText(listBean.getName());
        viewHolder.backImage.setImageResource(R.mipmap.def_loading);
        String urling = Constants.COMMON_URL+listBean.getIconurl();
        viewHolder.backImage.setTag(urling);
        ImageLoader.loadImage(urling, 400,200,new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap,String url) {
                if(url.equals(viewHolder.backImage.getTag())){
                viewHolder.backImage.setImageBitmap(bitMap);
                }
            }
        });
        String urlAutuor = Constants.COMMON_URL+listBean.getAuthorimg();
        viewHolder.authorImage.setImageResource(R.mipmap.author);
        viewHolder.authorImage.setTag(urlAutuor);
        ImageLoader.loadImage(urlAutuor, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap,String urltwo) {
               if(urltwo.equals(viewHolder.authorImage.getTag())){
                   viewHolder.authorImage.setImageBitmap(bitMap);}
            }
        });
        return view;
    }
    class ViewHolder{
        ImageView backImage;
        TextView newsName;
        ImageView authorImage;
        public ViewHolder(View view){
            backImage = (ImageView)view.findViewById(R.id.imageNews);
            newsName =(TextView)view.findViewById(R.id.nameNews);
            authorImage = (ImageView)view.findViewById(R.id.autor);
            view.setTag(this);
        }
    }
}

