package com.example.administrator.giftelves.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.adapters.HotGameAdapter;
import com.example.administrator.giftelves.adapters.HotGameAdapterOfGrid;
import com.example.administrator.giftelves.enties.HotGame;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;

/**
 *
 * Created by Administrator on 2016/12/27.
 */

public class HotGameFragment extends Fragment{
    private boolean isVisible;
    private boolean isPrepared;
    private HotGameAdapter hotGameAdapter;
    private HotGameAdapterOfGrid hotGameAdapterOfGrid;
    private ListView listView;
    private GridView gridView;
    private JsonLoader jsonLoader;
    private  HotGame game;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_activity_main,container,false);
        initView(view);
        isPrepared = true;
        initData();
        return view;
    }

    private void initData() {
        if(!isVisible||!isPrepared){
            return;
        }
        game = new HotGame();
        final Gson gson = new Gson();
        jsonLoader = new JsonLoader();
        jsonLoader.parseJson2String(Constants.HOT_GAME, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                game=gson.fromJson(json,HotGame.class);
                hotGameAdapter = new HotGameAdapter(getContext(),game.getInfo().getPush1());
                listView.setAdapter(hotGameAdapter);
                hotGameAdapterOfGrid = new HotGameAdapterOfGrid(getContext(),game.getInfo().getPush2());
                gridView.setAdapter(hotGameAdapterOfGrid);
            }
        });
    }
    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        gridView = (GridView) view.findViewById(R.id.gridOne);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        initData();
    }
}
