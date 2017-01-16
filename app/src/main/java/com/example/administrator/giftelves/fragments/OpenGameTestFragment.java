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
import com.example.administrator.giftelves.adapters.OpenTestAdapter;
import com.example.administrator.giftelves.enties.OpenGameTest;
import com.example.administrator.giftelves.enties.SpecialNews;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试的Fragment
 * Created by Administrator on 2016/12/30.
 */

public class OpenGameTestFragment extends Fragment {
    private PullToRefreshListView listView;
    private JsonLoader jsonLoader;
    private Gson gson;
    private OpenTestAdapter adapter;
    private Button footBtn;
    private LayoutInflater inflater;
    private List<OpenGameTest.InfoBean> mlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.open_game_test,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mlist = new ArrayList<>();
        gson = new Gson();
        jsonLoader = new JsonLoader();
        jsonLoader.parseJson2String(Constants.OPEN_TEST, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                mlist=gson.fromJson(json,OpenGameTest.class).getInfo();
                adapter = new OpenTestAdapter(getContext(),mlist);
                listView.setAdapter(adapter);
            }
        });
    }

    private void initView(View view) {
        listView = (PullToRefreshListView) view.findViewById(R.id.open_test_list);
        inflater = LayoutInflater.from(getContext());
//        View viewing = inflater.inflate(R.layout.footer,null);
//        footBtn = (Button) viewing.findViewById(R.id.footerButton);
//        footBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ("正在加载".equals(footBtn.getText().toString())) {
//                    return;
//                }
//                footBtn.setText("正在加载");
//                jsonLoader.parseJson2String(Constants.OPEN_TEST, new JsonLoader.JsonListener() {
//                    @Override
//                    public void JsonComplete(String json) {
//                        OpenGameTest test= gson.fromJson(json, OpenGameTest.class);
//                        List<OpenGameTest.InfoBean> listing =test.getInfo();
//                        mlist.addAll(listing);
//                        adapter.notifyDataSetChanged();
//                        footBtn.setText("加载更多");
//                    }
//                });
//            }
//        });
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
//        final ListView listViewing = listView.getRefreshableView();
//        listViewing.addFooterView(viewing);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mlist.clear();
                jsonLoader.parseJson2String(Constants.OPEN_TEST, new JsonLoader.JsonListener() {
                    @Override
                    public void JsonComplete(String json) {
                        OpenGameTest test=gson.fromJson(json,OpenGameTest.class);
                        mlist.addAll(test.getInfo());
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                jsonLoader.parseJson2String(Constants.OPEN_TEST, new JsonLoader.JsonListener() {
//                    @Override
//                    public void JsonComplete(String json) {
//                        OpenGameTest test=gson.fromJson(json,OpenGameTest.class);
//                        mlist.addAll(test.getInfo());
//                        adapter.notifyDataSetChanged();
//                        listView.onRefreshComplete();
//                    }
//                });
            }
        });
            }
        }

