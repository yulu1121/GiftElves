package com.example.administrator.giftelves.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.adapters.SpecialAdapter;
import com.example.administrator.giftelves.enties.SpecialNews;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 *新闻Fragment;
 * Created by Administrator on 2016/12/27.
 */

public class SpecialFragmentMagzine extends Fragment{
    private int pagenumber = 0;
    private Button footBtn;
    private LayoutInflater inflater;
    private SpecialNews specialNews;
    private JsonLoader jsonLoader;
    private PullToRefreshListView listView;
    private SpecialAdapter specialAdapter;
    final Gson gson = new Gson();
    private List<SpecialNews.ListBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.special_new_magzine,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        jsonLoader = new JsonLoader();
        specialNews = new SpecialNews();
        jsonLoader.parseJson2String(Constants.SPECIAL+pagenumber, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                specialNews = gson.fromJson(json,SpecialNews.class);
                list = specialNews.getList();
                specialAdapter = new SpecialAdapter(getContext(),list);
                listView.setAdapter(specialAdapter);
            }
        });
    }

    private void initView(View view) {
        listView = (PullToRefreshListView) view.findViewById(R.id.listView_special);
        inflater = LayoutInflater.from(getContext());
        View viewing = inflater.inflate(R.layout.footer, null);
        footBtn = (Button) viewing.findViewById(R.id.footerButton);
        footBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("正在加载".equals(footBtn.getText().toString())) {
                    return;
                }
                footBtn.setText("正在加载");
                pagenumber++;
                jsonLoader.parseJson2String(Constants.GIFT_URL +pagenumber, new JsonLoader.JsonListener() {
                    @Override
                    public void JsonComplete(String json) {
                         SpecialNews news= gson.fromJson(json, SpecialNews.class);
                        List<SpecialNews.ListBean> listing = news.getList();
                        list.addAll(listing);
                        specialAdapter.notifyDataSetChanged();
                        footBtn.setText("加载更多");
                    }
                });
            }
        });
        ListView listView1 = listView.getRefreshableView();
        listView1.addFooterView(viewing);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagenumber = 0;
                list.clear();
                jsonLoader.parseJson2String(Constants.SPECIAL + pagenumber, new JsonLoader.JsonListener() {
                   @Override
                   public void JsonComplete(String json) {
                       SpecialNews news= gson.fromJson(json, SpecialNews.class);
                       List<SpecialNews.ListBean> listing = news.getList();
                       list.addAll(listing);
                       specialAdapter.notifyDataSetChanged();
                       listView.onRefreshComplete();
                   }
               });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }
}


