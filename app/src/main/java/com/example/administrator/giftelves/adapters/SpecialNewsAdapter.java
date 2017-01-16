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
import com.example.administrator.giftelves.enties.SpecialNewsFormation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/8.
 */
public class SpecialNewsAdapter extends BaseAdapter {
    private List<SpecialNewsFormation.ListBean> mlist;
    private Context mcontext;
    private LayoutInflater inflater;
    public SpecialNewsAdapter(Context context,List<SpecialNewsFormation.ListBean> list){
        this.mcontext = context;
        this.mlist = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return null==mlist?0:mlist.size();
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
            view = inflater.inflate(R.layout.special_news_listview,parent,false);
            viewHolder = new ViewHolder(view);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final SpecialNewsFormation.ListBean listBean = mlist.get(position);
        viewHolder.gName.setText(listBean.getAppname());
        viewHolder.gType.setText(listBean.getTypename());
        viewHolder.gSize.setText("大小:"+listBean.getAppsize());
        viewHolder.explain.setText(listBean.getDescs());
        viewHolder.imageView.setImageResource(R.mipmap.gift);
        String url = Constants.COMMON_URL+listBean.getIconurl();
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
                intent.putExtra("hotId",listBean.getAppid());
                mcontext.startActivity(intent);
            }
        });
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView gName;
        TextView gSize;
        TextView gType;
        TextView explain;
        Button btn;
        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.imageView);
            gName = (TextView)view.findViewById(R.id.gameName);
            gSize = (TextView)view.findViewById(R.id.gameSize);
            gType=(TextView)view.findViewById(R.id.gameType);
            explain = (TextView)view.findViewById(R.id.gameExplain);
            btn = (Button)view.findViewById(R.id.getGift);
            view.setTag(this);
        }
    }
}
