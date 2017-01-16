package com.example.administrator.giftelves.selflistview;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 * Created by Administrator on 2017/1/11.
 */

public class MySlidingView extends SlidingPaneLayout {
    public MySlidingView(Context context) {
       this(context,null);
    }

    public MySlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(this.isOpen()){
            return super.onInterceptTouchEvent(ev);
        }else {
            MotionEvent cancel = MotionEvent.obtain(ev);
            cancel.setAction(MotionEvent.ACTION_CANCEL);
            return super.onInterceptTouchEvent(cancel);
        }
    }
}
