package com.example.administrator.giftelves.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.activitys.GiftInformationActivity;
import com.example.administrator.giftelves.adapters.GiftAdapter;
import com.example.administrator.giftelves.enties.Gift;
import com.example.administrator.giftelves.utils.ImageLoader;
import com.example.administrator.giftelves.utils.JsonLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 礼包的Fragment
 * Created by Administrator on 2016/12/27.
 */

public class GiftFragment extends Fragment{
    private JsonLoader jsonLoader;
    private LayoutInflater inflater;
    private PullToRefreshListView refreshListView;
    private GiftAdapter giftAdapter;
    private ViewPager viewPager;
    private Gift mgift;
    private  static int pagernumber =1;
    //视图的集合
    private List<View> viewList;
    //imageView的集合存放圆点
    private List<ImageView> dotList;
    //圆点的布局
    private LinearLayout linearLayout;
    //原来选中圆点的索引
    private int currentDot = 0;
    private Handler mhandler=null;
    //当前页面
    private int currentViewPager = 0;
    private Button footBtn;
    private List<Gift.ListBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gift_activity_main,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        jsonLoader = new JsonLoader();
        final Gson gson = new Gson();
        jsonLoader.parseJson2String(Constants.GIFT_URL + pagernumber, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                mgift = gson.fromJson(json,Gift.class);
                list = new ArrayList<>();
                list = mgift.getList();
                giftAdapter = new GiftAdapter(getContext(),list);
                refreshListView.setAdapter(giftAdapter);
                refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Gift.ListBean listBean = list.get(position-2);
                        String giftId=listBean.getId();
                        Intent intent = new Intent(getActivity(), GiftInformationActivity.class);
                        intent.putExtra("giftId",giftId);
                        startActivity(intent);
                    }
                });
                initViewPager(mgift.getAd());
                //设置viewpager的适配器
                viewPager.setAdapter(new GiftViewPagerAdapter());
                //初始化圆点
                initDots(mgift.getAd().size());
                //启动自动轮播
                if(null==mhandler){
                    mhandler = new Handler();
                    mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //页面到底重复执行
                        if(currentViewPager==mgift.getAd().size()-1){
                                currentViewPager=0;
                        }else {
                            currentViewPager++;
                        }
                        viewPager.setCurrentItem(currentViewPager,false);
                        mhandler.postDelayed(this,3000);
                    }
                },3000);
                }
            }
        });
    }

    private void initView(final View view) {
        inflater = LayoutInflater.from(getContext());
        final Gson gsoning = new Gson();
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.listView_gift);
        //添加底部视图
//      View footerView = inflater.inflate(R.layout.footer,null);
//        footBtn=(Button) footerView.findViewById(R.id.footerButton);
//        footBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if("正在加载".equals(footBtn.getText().toString())){
//                    return;
//                }
//                footBtn.setText("正在加载");
//                pagernumber++;
//                jsonLoader.parseJson2String(Constants.GIFT_URL + pagernumber, new JsonLoader.JsonListener() {
//                    @Override
//                    public void JsonComplete(String json) {
//                        mgift=gsoning.fromJson(json,Gift.class);
//                        List<Gift.ListBean> listing=mgift.getList();
//                        list.addAll(listing);
//                        giftAdapter.notifyDataSetChanged();
//                        footBtn.setText("加载更多");
//                    }
//                });
//            }
//        });
        //添加头部视图
        View viewing = inflater.inflate(R.layout.gift_header,null);
        viewPager = (ViewPager) viewing.findViewById(R.id.viewPager);
        linearLayout = (LinearLayout)viewing.findViewById(R.id.dot_layout);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //将原来的页面设为未被选择状态
                dotList.get(currentDot).setEnabled(true);
                //将现在的页面设为选择状态
                dotList.get(position).setEnabled(false);
                currentDot = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //获得本listView;
        ListView listView = refreshListView.getRefreshableView();
        listView.addHeaderView(viewing);
//        listView.addFooterView(footerView);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新菜单
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            //加载下拉菜单
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pagernumber=1;
                //加载第一页
                jsonLoader.parseJson2String(Constants.GIFT_URL + pagernumber, new JsonLoader.JsonListener() {
                    @Override
                    public void JsonComplete(String json) {
                        mgift=gsoning.fromJson(json,Gift.class);
                        list.clear();
                        List<Gift.ListBean> listing=mgift.getList();
                        list.addAll(listing);
                        giftAdapter.notifyDataSetChanged();
                        refreshListView.onRefreshComplete();
                    }
                });
            }

            @Override
            //刷新上拉菜单
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //更新页面
                pagernumber++;
                jsonLoader.parseJson2String(Constants.GIFT_URL + pagernumber, new JsonLoader.JsonListener() {
                    @Override
                    public void JsonComplete(String json) {
                        mgift=gsoning.fromJson(json,Gift.class);
                        List<Gift.ListBean> listing=mgift.getList();
                        list.addAll(listing);
                        giftAdapter.notifyDataSetChanged();
                        refreshListView.onRefreshComplete();
                    }
                });

            }
        });
    }

    //初始化ViewPager；
    private void initViewPager(List<Gift.AdBean> list){
        viewList = new ArrayList<>();
        for(Gift.AdBean ad: list){
            View view = inflater.inflate(R.layout.ad_image_layout,null);
            final ImageView imageView=(ImageView) view.findViewById(R.id.ad_imageView);
            imageView.setImageResource(R.mipmap.dashen);
            String urling = Constants.COMMON_URL+ad.getIconurl();
            imageView.setTag(urling);
            ImageLoader.loadImage(urling, new ImageLoader.ImageListener() {
                @Override
                public void ImageComplete(Bitmap bitMap,String url) {
                    if(url.equals(imageView.getTag())){
                        imageView.setImageBitmap(bitMap);
                    }
                }
            });
            viewList.add(view);
        }
    }
    private void initDots(int count){
        dotList = new ArrayList<>();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置左右间距
        lp.setMargins(5,0,5,0);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(getContext());
            //设置选择器
            imageView.setImageResource(R.drawable.dot_selector);
            dotList.add(imageView);
            //添加视图和参数
            linearLayout.addView(imageView,lp);
        }
        //默认第一个被选中
        dotList.get(0).setEnabled(false);
    }
    class GiftViewPagerAdapter extends PagerAdapter{
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(viewList.get(position));
            View views = viewList.get(position);
            //viewPager的监听要放在适配器中
            views.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gift.AdBean adBean = mgift.getAd().get(position);
                    String adGiftId = adBean.getGiftid();
                    Intent intent = new Intent(getActivity(),GiftInformationActivity.class);
                    intent.putExtra("giftId",adGiftId);
                    startActivity(intent);
                }
            });
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public int getCount() {
            return viewList==null?0:viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
