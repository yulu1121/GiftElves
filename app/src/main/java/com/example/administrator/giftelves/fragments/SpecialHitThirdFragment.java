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
import com.example.administrator.giftelves.adapters.SpecialHitThirdAdapter;
import com.example.administrator.giftelves.enties.SpecialHitThird;
import com.example.administrator.giftelves.enties.SpecialNews;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * 这是暴打星期三的Fragment
 * Created by Administrator on 2016/12/29.
 */

public class SpecialHitThirdFragment extends Fragment {
    private PullToRefreshListView mlistView;
    private SpecialHitThirdAdapter adapter;
    private List<SpecialHitThird.ListBean> mlist;
    private Button footBtn;
    private Gson gson;
    private int pageNumber = 0;
    private JsonLoader jsonLoader;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.special_hit_third_magazine,null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        gson = new Gson();
        jsonLoader = new JsonLoader();
        jsonLoader.parseJson2String(Constants.HIT_THIRD + pageNumber, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                mlist= gson.fromJson(json,SpecialHitThird.class).getList();
                adapter = new SpecialHitThirdAdapter(getContext(),mlist);
                mlistView.setAdapter(adapter);
            }
        });
    }

    private void initView(View view) {
        mlistView = (PullToRefreshListView) view.findViewById(R.id.hit_third_list);
        View viewing=LayoutInflater.from(getContext()).inflate(R.layout.footer,null);
        footBtn = (Button) viewing.findViewById(R.id.footerButton);
        mlistView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ListView listView = mlistView.getRefreshableView();
        footBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("正在加载".equals(footBtn.getText().toString())) {
                    return;
                }
                footBtn.setText("正在加载");
                pageNumber++;
                jsonLoader.parseJson2String(Constants.GIFT_URL +pageNumber, new JsonLoader.JsonListener() {
                    @Override
                    public void JsonComplete(String json) {
                        SpecialHitThird hitThird= gson.fromJson(json, SpecialHitThird.class);
                        List<SpecialHitThird.ListBean> listing = hitThird.getList();
                        mlist.addAll(listing);
                        adapter.notifyDataSetChanged();
                        footBtn.setText("加载更多");
                    }
                });
            }
        });
        listView.addFooterView(viewing);
        mlistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNumber = 0;
                mlist.clear();
                jsonLoader.parseJson2String(Constants.HIT_THIRD + pageNumber, new JsonLoader.JsonListener() {
                    @Override
                    public void JsonComplete(String json) {
                        SpecialHitThird hitThird=gson.fromJson(json,SpecialHitThird.class);
                        List<SpecialHitThird.ListBean> listing = hitThird.getList();
                        mlist.addAll(listing);
                        adapter.notifyDataSetChanged();
                        mlistView.onRefreshComplete();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }
}
