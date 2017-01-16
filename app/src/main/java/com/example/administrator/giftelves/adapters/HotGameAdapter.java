package com.example.administrator.giftelves.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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

public class HotGameAdapter extends BaseAdapter{
    private List<HotGame.InfoBean.Push1Bean> push1BeanList;
    private Context context;
    private LayoutInflater inflate;
    public HotGameAdapter(Context context,List<HotGame.InfoBean.Push1Bean> list){
        this.context = context;
        this.push1BeanList = list;
        list = new ArrayList<>();
        inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return null==push1BeanList?0:push1BeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return push1BeanList.get(position);
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
            view = inflate.inflate(R.layout.hot_activity_item, parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final HotGame.InfoBean.Push1Bean game1=push1BeanList.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HotGameActivity.class);
                intent.putExtra("hotId",game1.getAppid());
                context.startActivity(intent);
            }
        });
        viewHolder.gameName.setText(game1.getName());
        viewHolder.gameType.setText(game1.getTypename());
        viewHolder.gameSize.setText(game1.getSize());
        SpannableString str = new SpannableString(String.valueOf(game1.getClicks()));
        str.setSpan(new ForegroundColorSpan(Color.RED),0,str.length(),str.SPAN_INCLUSIVE_INCLUSIVE);
        viewHolder.clicks.setText(str);
        viewHolder.imageView.setImageResource(R.mipmap.gift);
        String urling = Constants.COMMON_URL+game1.getLogo();
        viewHolder.imageView.setTag(urling);
        ImageLoader.loadImage(Constants.COMMON_URL+game1.getLogo(), new ImageLoader.ImageListener() {
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
        TextView gameType;
        TextView gameSize;
        TextView clicks;
        public ViewHolder(View view){
            imageView =(ImageView)view.findViewById(R.id.imageView);
            gameName = (TextView)view.findViewById(R.id.gameName);
            gameType = (TextView)view.findViewById(R.id.gameType);
            gameSize = (TextView)view.findViewById(R.id.gameSize);
            clicks = (TextView)view.findViewById(R.id.clicks);
            view.setTag(this);
        }
    }
}
