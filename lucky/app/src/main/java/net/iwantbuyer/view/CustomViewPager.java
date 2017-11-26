package net.iwantbuyer.view;

/**
 * Created by admin on 2016/10/18.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自动适应高度的ViewPager
 *
 * @author
 */
public class CustomViewPager extends ViewPager {

    public int allHeight = 0;
    public int luckyHeight = 0;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            int position = this.getCurrentItem();
//            View child = getChildAt(i);
            View child = getChildAt(position);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(i == 0) {
                allHeight = h;
            }else if(i == 1) {
                luckyHeight = h;
            }
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
