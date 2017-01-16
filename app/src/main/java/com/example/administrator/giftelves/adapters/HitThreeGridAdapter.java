package com.example.administrator.giftelves.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.giftelves.activitys.HotGameActivity;
import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.HitThirdInformation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/8.
 */

public class HitThreeGridAdapter extends BaseAdapter{
    private Context mcontext;
    private List<HitThirdInformation.ListBean> mlist;
    private LayoutInflater inflater;
    public HitThreeGridAdapter(Context context,List<HitThirdInformation.ListBean> list){
        this.mcontext = context;
        this.mlist = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mlist.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if(null==view){
            view = inflater.inflate(R.layout.hit_grid_item,parent,false);
            viewHolder = new ViewHolder(view);
        }else {
            viewHolder =(ViewHolder) view.getTag();
        }
        final HitThirdInformation.ListBean listBeaning = mlist.get(position);
        viewHolder.gName.setText(listBeaning.getAppname());
        viewHolder.imageView.setImageResource(R.mipmap.gift);
        String url = Constants.COMMON_URL+listBeaning.getAppicon();
        viewHolder.imageView.setTag(url);
        ImageLoader.loadImage(url, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(viewHolder.imageView.getTag())){
                    viewHolder.imageView.setImageBitmap(bitMap);
                }
            }
        });
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, HotGameActivity.class);
                intent.putExtra("hotId",listBeaning.getAppid());
                mcontext.startActivity(intent);
            }
        });
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView gName;
        Button btn;
        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.hit_imageOfGift);
            gName=(TextView)view.findViewById(R.id.hit_giftingName);
            btn = (Button)view.findViewById(R.id.hit_download);
            view.setTag(this);
        }
    }
}

