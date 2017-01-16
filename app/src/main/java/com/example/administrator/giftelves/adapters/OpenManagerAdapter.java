package com.example.administrator.giftelves.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.giftelves.activitys.OpenGameManagerActivity;
import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.OpenGameManager;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2016/12/30.
 */

public class OpenManagerAdapter extends BaseExpandableListAdapter {
    private Map<String,List<OpenGameManager.InfoBean>> map;
    private Context mcontext;
    private LayoutInflater inflater;
    public OpenManagerAdapter(Context context,OpenGameManager openGameManager){
        this.mcontext = context;
        inflater = LayoutInflater.from(context);
        if(openGameManager!=null){
            map = new LinkedHashMap<>();
            for(OpenGameManager.InfoBean infoBean:openGameManager.getInfo()){
                String addtime = infoBean.getAddtime();
                if(!map.containsKey(addtime)){
                    map.put(addtime,new ArrayList<OpenGameManager.InfoBean>());
                }
                map.get(addtime).add(infoBean);
            }
        }
    }
    @Override
    public int getGroupCount() {
        return map.keySet().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = (String) map.keySet().toArray()[groupPosition];
        List<OpenGameManager.InfoBean> mlist = map.get(key);
        return null==mlist?0:mlist.size();
    }
    //获得组视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String key= (String) map.keySet().toArray()[groupPosition];
        TextView textView = new TextView(mcontext);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dating=format.format(date);
        if(dating.equals(key)){
            textView.setText(key+" 今日开服");
        }else {
            textView.setText(key);
        }
        textView.setTextSize(15f);
        textView.setTextColor(Color.GREEN);
       return textView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;
        final ViewHolder viewHolder;
        if(null==view){
            view = inflater.inflate(R.layout.open_game_manager_item,parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        String key= (String) map.keySet().toArray()[groupPosition];
       final List<OpenGameManager.InfoBean> mlist= map.get(key);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=mlist.get(childPosition).getGid();
                Intent intent = new Intent(mcontext, OpenGameManagerActivity.class);
                intent.putExtra("managerId",id);
                mcontext.startActivity(intent);
            }
        });
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=mlist.get(childPosition).getGid();
                Intent intent = new Intent(mcontext, OpenGameManagerActivity.class);
                intent.putExtra("managerId",id);
                mcontext.startActivity(intent);
            }
        });
        viewHolder.gname.setText(mlist.get(childPosition).getGname());
        viewHolder.openTime.setText(mlist.get(childPosition).getLinkurl());
        viewHolder.company.setText(mlist.get(childPosition).getOperators());
        viewHolder.area.setText(mlist.get(childPosition).getArea());
        final String url = Constants.COMMON_URL+mlist.get(childPosition).getIconurl();
        viewHolder.imageView.setImageResource(R.mipmap.gift);
        viewHolder.imageView.setTag(url);
        ImageLoader.loadImage(url, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(viewHolder.imageView.getTag())){
                    viewHolder.imageView.setImageBitmap(bitMap);
                }
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class ViewHolder{
        ImageView imageView;
        TextView gname;
        TextView openTime;
        TextView area;
        TextView company;
        Button btn;
        public ViewHolder(View view){
            imageView=(ImageView) view.findViewById(R.id.open_manager_image);
            gname = (TextView) view.findViewById(R.id.open_name);
            openTime =(TextView) view.findViewById(R.id.open_manager_time);
            area = (TextView) view.findViewById(R.id.open_manager_name);
            company = (TextView)view.findViewById(R.id.open_company);
            btn = (Button)view.findViewById(R.id.open_look_btn);
            view.setTag(this);
        }
    }
    @Override
    public Object getGroup(int groupPosition) {
     return  null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
