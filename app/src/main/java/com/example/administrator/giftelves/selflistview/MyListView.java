package com.example.administrator.giftelves.selflistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *
 * Created by Administrator on 2017/1/8.
 */

public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //将ListView的高度设为最大

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获得最大值
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
