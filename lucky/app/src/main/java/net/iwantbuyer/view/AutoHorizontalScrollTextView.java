package net.iwantbuyer.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自动横向滑动的TextView（类似现实中的电子屏效果）
 */
public class AutoHorizontalScrollTextView extends TextView{
    public AutoHorizontalScrollTextView(Context context) {
        this(context,null);

    }

    public AutoHorizontalScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AutoHorizontalScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClickable(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
