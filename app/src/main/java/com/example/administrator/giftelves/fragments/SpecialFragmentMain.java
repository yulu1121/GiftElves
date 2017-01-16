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
 * Special的主Fragemnt
 * Created by Administrator on 2016/12/29.
 */

public class SpecialFragmentMain extends Fragment{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.special_main,null);
        initFragments();
        initView(view);
        return view;
    }

    private void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.table_layout_special);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_special);
        viewPager.setAdapter(new MyFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i <tabLayout.getTabCount() ; i++) {
            tabLayout.getTabAt(0).setText("暴打星期三");
            tabLayout.getTabAt(1).setText("新游周刊");
        }
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new SpecialHitThirdFragment());
        fragmentList.add(new SpecialFragmentMagzine());
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
