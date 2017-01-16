package com.example.administrator.giftelves.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.adapters.OpenManagerAdapter;
import com.example.administrator.giftelves.enties.OpenGameManager;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 开服
 * Created by Administrator on 2016/12/30.
 */

public class OpenGameManagerFragment extends Fragment {
    private ExpandableListView mlistView;
    private List<OpenGameManager.InfoBean> mlist;
    private OpenManagerAdapter openManagerAdapter;
    private JsonLoader jsonLoader;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.open_game_manager,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mlist = new ArrayList<>();
       final Gson gson = new Gson();
        jsonLoader = new JsonLoader();
        jsonLoader.parseJson2String(Constants.OPEN_MANAGER, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                OpenGameManager openGameManager = gson.fromJson(json, OpenGameManager.class);
                mlist = openGameManager.getInfo();
                openManagerAdapter = new OpenManagerAdapter(getContext(),openGameManager);
                mlistView.setAdapter(openManagerAdapter);
                for (int i = 0; i <openManagerAdapter.getGroupCount() ; i++) {
                    mlistView.expandGroup(i);
                }
            }
        });
    }

    private void initView(View view) {
        mlistView = (ExpandableListView) view.findViewById(R.id.expanded_open_manager);
    }
}
