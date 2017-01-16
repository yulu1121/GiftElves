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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.activitys.GiftInformationActivity;
import com.example.administrator.giftelves.enties.SearchFormation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/11.
 */

public class SearchAdapter extends BaseAdapter{
    private List<SearchFormation.ListBean> list ;
    private Context context;
    private LayoutInflater inflate;
    public SearchAdapter(Context context,List<SearchFormation.ListBean> list){
        this.context=context;
        this.list = list;
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
            view = inflate.inflate(R.layout.gift_activity_item, parent, false);
            viewHolder =new ViewHolder(view);
        }else{
            viewHolder  = (ViewHolder) view.getTag();
        }
        final SearchFormation.ListBean gifting= list.get(position);
        viewHolder.gName.setText(gifting.getGname());
        viewHolder.gType.setText(gifting.getGiftname());
        SpannableString str = new SpannableString("剩余:"+gifting.getNumber());
        str.setSpan(new ForegroundColorSpan(Color.GREEN),3,str.length(),str.SPAN_INCLUSIVE_INCLUSIVE);
        viewHolder.extra.setText(str);
        viewHolder.time.setText("时间:"+gifting.getAddtime());
        viewHolder.imageView.setImageResource(R.mipmap.gift);
        String urling = Constants.COMMON_URL+gifting.getIconurl();
        viewHolder.imageView.setTag(urling);
        ImageLoader.loadImage(urling,new ImageLoader.ImageListener() {

            @Override
            public void ImageComplete(Bitmap bitMap, String url) {
                if(url.equals(viewHolder.imageView.getTag())){
                    viewHolder.imageView.setImageBitmap(bitMap);
                }

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String giftId = gifting.getId();
                Intent intent = new Intent();
                intent.setClass(context,GiftInformationActivity.class);
                intent.putExtra("giftId",giftId);
                context.startActivity(intent);
            }
        });
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String giftId = gifting.getId();
                Intent intent = new Intent();
                intent.setClass(context,GiftInformationActivity.class);
                intent.putExtra("giftId",giftId);
                context.startActivity(intent);
            }
        });
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView gName;
        TextView  gType;
        TextView  extra;
        TextView  time;
        Button btn;
        public ViewHolder(View view){
            imageView = (ImageView)view.findViewById(R.id.imageOne);
            gName = (TextView)view.findViewById(R.id.textName);
            gType = (TextView)view.findViewById(R.id.textType);
            extra = (TextView)view.findViewById(R.id.extra);
            time = (TextView)view.findViewById(R.id.time);
            btn  = (Button)view.findViewById(R.id.getGift);
            view.setTag(this);
        }
    }
}
