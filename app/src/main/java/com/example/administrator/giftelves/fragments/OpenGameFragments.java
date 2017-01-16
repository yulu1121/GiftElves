package com.example.administrator.giftelves.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.giftelves.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 开服的Fragment
 * Created by Administrator on 2016/12/30.
 */

public class OpenGameFragments extends Fragment {
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.open_game_main,container,false);
        initFragements();
        initView(view);
        return view;
    }

    private void initFragements() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new OpenGameManagerFragment());
        fragmentList.add(new OpenGameTestFragment());
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.open_viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.open_tab);
        viewPager.setAdapter(new MyFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("开服");
        tabLayout.getTabAt(1).setText("开测");
    }

    class MyFragmentAdapter extends FragmentPagerAdapter{

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
