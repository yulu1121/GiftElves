package com.example.administrator.giftelves.activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.fragments.Constants;
import com.example.administrator.giftelves.fragments.GiftFragment;
import com.example.administrator.giftelves.fragments.HotGameFragment;
import com.example.administrator.giftelves.fragments.OpenGameFragments;
import com.example.administrator.giftelves.fragments.SpecialFragmentMain;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> list;
    private Button sildBtn = null;
    private TextView menuText;
    private TextView seachTextView;
    private Button slidBtn;
    private List<RadioButton> mlist;//存放RadioButton的集合
    private boolean isSplash = false;
    private SlidingPaneLayout slidingPaneLayout;
    private View mainView;
    private int currentRadio = 0;//最开始radioButton的下标
    private ViewPager viewPager;
    private GiftFragment giftFragment = new GiftFragment();
    private HotGameFragment hotGameFragment = new HotGameFragment();
    private SpecialFragmentMain specialFragmentMain = new SpecialFragmentMain();
    private OpenGameFragments openGameFragments = new OpenGameFragments();

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isSplash  =true;
        if(isSplash){
            isSplash = false;
            Intent intent = new Intent(this,SplashActivity.class);
            startActivity(intent);
        }
        if(isNetWorkAvailable(this)){
            initFragments();
            initViews();
        }else {
            Toast.makeText(this, "亲，你还没有联网", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isNetWorkAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info!=null){
            return info.isConnected();
        }else {
            return false;
        }
    }
    private void initViews() {
        mlist = new ArrayList<>();
        mainView = findViewById(R.id.activity_main);
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_layout);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                mainView.setScaleX(1-slideOffset*0.4f);
                mainView.setScaleY(1-slideOffset*0.4f);
            }

            @Override
            public void onPanelOpened(View panel) {
        }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
        findViewById(R.id.gamegift).setOnClickListener(this);
        mlist.add((RadioButton) findViewById(R.id.gamegift));
        findViewById(R.id.hotgame).setOnClickListener(this);
        mlist.add((RadioButton) findViewById(R.id.hotgame));
        findViewById(R.id.opengame).setOnClickListener(this);
        mlist.add((RadioButton) findViewById(R.id.opengame));
        findViewById(R.id.special).setOnClickListener(this);
        mlist.add((RadioButton) findViewById(R.id.special));
        mlist.get(currentRadio).setEnabled(false);
        viewPager = (ViewPager) findViewById(R.id.viewPager_main);
        viewPager.setAdapter(new ViewFragmentAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mlist.get(currentRadio).setEnabled(true);
                mlist.get(position).setEnabled(false);
                currentRadio = position;
                Fragment fragment = list.get(position);
                if(fragment==(giftFragment)){
                    menuText.setText("礼包精灵");
                    seachTextView.setVisibility(View.VISIBLE);
                }else if(fragment==(hotGameFragment)){
                    menuText.setText("热门");
                    seachTextView.setVisibility(View.GONE);
                }else if(fragment==(specialFragmentMain)){
                    menuText.setText("特别企划");
                    seachTextView.setVisibility(View.GONE);
                } else if(fragment==(openGameFragments)){
                    menuText.setText("开服");
                    seachTextView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        menuText = (TextView) findViewById(R.id.menu);
        sildBtn = (Button) findViewById(R.id.radioselect);
        menuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
               startActivity(intent);
            }
        });
        seachTextView  =(TextView)findViewById(R.id.search);
        seachTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        sildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                slidingPaneLayout.openPane();

            }
        });
    }

    private void initFragments() {
        list = new ArrayList<>();
        list.clear();
        list.add(giftFragment);
        list.add(hotGameFragment);
        list.add(openGameFragments);
        list.add(specialFragmentMain);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gamegift:
                viewPager.setCurrentItem(0);
                menuText.setText("礼包精灵");
                seachTextView.setVisibility(View.VISIBLE);
                break;
            case R.id.hotgame:
                viewPager.setCurrentItem(1);
                menuText.setText("热门");
                seachTextView.setVisibility(View.GONE);
                break;
            case R.id.opengame:
                viewPager.setCurrentItem(2);
                menuText.setText("开服");
                seachTextView.setVisibility(View.GONE);
                break;
            case R.id.special:
                viewPager.setCurrentItem(3);
                menuText.setText("特别企划");
                seachTextView.setVisibility(View.GONE);
                break;
//            case R.id.aboutme:
//                changeFragment(meFragment);
//                break;
        }
    }
    class ViewFragmentAdapter extends FragmentPagerAdapter{

        public ViewFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
