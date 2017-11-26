package net.iwantbuyer.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import net.iwantbuyer.R;

/**
 * Created by admin on 2016/9/13.
 */
public class StatusBarUtils {
    public Context context;
    public StatusBarUtils(Context context) {
        this.context = context;
    }

    //沉浸式状态栏
    public void statusBar(){
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(((Activity)context));
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.bg_while);//通知栏所需颜色
//            String status = context.getIntent().getStringExtra("status");
//            if("black".equals(status)) {
//                tintManager.setStatusBarTintResource(R.color.text_black);//通知栏所需颜色
//            }else if("purple".equals(status)){
//                tintManager.setStatusBarTintResource(R.color.abc_ziser);//通知栏所需颜色
//            } else{
//                tintManager.setStatusBarTintResource(R.color.bg_while);//通知栏所需颜色
//            }
        }
    }

    //控制版本 沉浸式状态栏
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = ((Activity)context).getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
