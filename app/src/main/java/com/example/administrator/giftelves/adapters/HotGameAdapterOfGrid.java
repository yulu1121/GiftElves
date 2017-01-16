package com.example.administrator.giftelves.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.giftelves.activitys.HotGameActivity;
import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.HotGame;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/12/28.
 */

public class HotGameAdapterOfGrid extends BaseAdapter{
    private List<HotGame.InfoBean.Push2Bean> list;
    private Context context;
    private LayoutInflater inflate;
    public HotGameAdapterOfGrid(Context context,List<HotGame.InfoBean.Push2Bean> list){
        this.context = context;
        this.list = list;
        list = new ArrayList<>();
        inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return null==list?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if(null==view){
            view = inflate.inflate(R.layout.grid_item, parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final HotGame.InfoBean.Push2Bean game = list.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HotGameActivity.class);
                String id = game.getAppid();
                intent.putExtra("hotId",id);
                context.startActivity(intent);
            }
        });
        viewHolder.gameName.setText(game.getName());
        String urling = Constants.COMMON_URL+game.getLogo();
        viewHolder.imageView.setImageResource(R.mipmap.gift);
        viewHolder.imageView.setTag(urling);
        ImageLoader.loadImage(urling, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap,String url) {
                if(url.equals(viewHolder.imageView.getTag())){
                    viewHolder.imageView.setImageBitmap(bitMap);
                }
            }
        });
        return view;
    }

    /**
     * @author yulu
     *	定义ViewHolder的内部类
     */
    class ViewHolder{
        ImageView imageView;
        TextView gameName;
        public ViewHolder(View view){
            imageView =(ImageView)view.findViewById(R.id.imageOfGift);
            gameName = (TextView)view.findViewById(R.id.giftingName);
            view.setTag(this);
        }
    }
}
