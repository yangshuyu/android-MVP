package net.iwantbuyer.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.iwantbuyer.R;

/**
 * Created by admin on 2016/12/19.
 * 自定义Toast
 */
public class CustomToast {
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    Toast toast;
    TextView tvToastText;
    Context context;

    public CustomToast(Context context) {
        this.context = context;
        toast = new Toast(context);

        /**
         * 这里：根据自定义的toast xml样式进行加载展示，类似于其他xml对应的view一样
         *      通过InflaterLayout 展示整个rootView 再将内部的toast附着在这个viewRoot上
         */
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastRoot = inflater.inflate(R.layout.custom_toast, null);
        tvToastText = (TextView) toastRoot.findViewById(R.id.toast_text);

        toast.setView(toastRoot);
    }

    public void setDuration(int duration) {
        toast.setDuration(duration);
    }

    public void setText(CharSequence text) {
        tvToastText.setText(text);
    }


    /**
     * 这里默认选用屏幕中心为坐标原点，
     * @param xOffset X轴偏离中心的距离（往右为正）
     * @param yOffset Y轴偏离中心的距离（往下为正）
     */
    public void setGravity(int xOffset, int yOffset) {
        toast.setGravity(Gravity.CENTER, xOffset, yOffset);
    }

    /**
     *
     * @param context
     * @param text     toast展示文本
     * @param duration toast展示时间
     * @param xOffset  设置toast偏离中心X方向的距离
     * @param yOffset  设置toast偏离中心Y方向的距离
     * @return
     */
    public static CustomToast makeText(Context context, CharSequence text, int duration, int xOffset, int yOffset ) {
        CustomToast customedToast = new CustomToast(context);
        customedToast.setText(text);
        customedToast.setDuration(duration);
//        customedToast.setGravity(xOffset, yOffset);
        return customedToast;
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){

        }
    };
    public void show() {
//        toast.cancel();
        toast.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               toast.cancel();
            }
        },1000);
    }
}
