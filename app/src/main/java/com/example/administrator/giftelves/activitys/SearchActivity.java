package com.example.administrator.giftelves.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.adapters.GiftAdapter;
import com.example.administrator.giftelves.adapters.SearchAdapter;
import com.example.administrator.giftelves.enties.SearchFormation;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/11.
 */

public class SearchActivity extends AppCompatActivity {
    private EditText editText;
    private TextView search;
    private ListView listView;
    private SearchAdapter adapter;
    private List<String> mlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        initView();
        initData();
    }

    private void initData() {
        final Gson gson = new Gson();
        final JsonLoader jsonLoader = new JsonLoader();
        jsonLoader.parseJson2StringPost(Constants.SEARCH_FORMATION, mlist, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                SearchFormation formation = gson.fromJson(json, SearchFormation.class);
                adapter  = new SearchAdapter(SearchActivity.this,formation.getList());
                listView.setAdapter(adapter);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlist.add("key="+editText.getText().toString());
                jsonLoader.parseJson2StringPost(Constants.SEARCH_ACTUAL, mlist, new JsonLoader.JsonListener() {
                    @Override
                    public void JsonComplete(String json) {
                        SearchFormation formation = gson.fromJson(json, SearchFormation.class);
                        adapter  = new SearchAdapter(SearchActivity.this,formation.getList());
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }
    private void initView() {
        mlist = new ArrayList<>();
        editText = (EditText) findViewById(R.id.search_information);
        search = (TextView)findViewById(R.id.actual_search);
        listView = (ListView)findViewById(R.id.search_listView);
    }

    public void oncClickBack(View view) {
        finish();
    }
}
