package net.iwantbuyer.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;

import net.iwantbuyer.R;
import net.iwantbuyer.module.product.view.fragment.ProductPager;
import net.iwantbuyer.module.product.view.fragment.ShowPager;
import net.iwantbuyer.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private FrameLayout fl_main;
    public RadioGroup rg_main;
    private List<Fragment> list;

    private RadioButton rb_main_homepager;
    private RadioButton rb_main_buycoins;
    private RadioButton rb_main_newresult;
    private RadioButton rb_main_show;
    private RadioButton rb_main_me;
    private RelativeLayout rl_main;


    private FragmentManager fragmentManager;
    private ProductPager homePager;
    private ProductPager buyCoinPager;
    private ProductPager winningPager;
    private ShowPager showPager;
    private ProductPager mePager;

    public int id;

    AuthenticationAPIClient client;
    Auth0 auth0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) { // “内存重启”时调用

            //从fragmentManager里面找到fragment
            homePager = (ProductPager) fragmentManager.findFragmentByTag(ProductPager.class.getName());
            buyCoinPager = (ProductPager) fragmentManager.findFragmentByTag(ProductPager.class.getName());
            winningPager = (ProductPager) fragmentManager.findFragmentByTag(ProductPager.class.getName());
            showPager = (ShowPager) fragmentManager.findFragmentByTag(ShowPager.class.getName());
            mePager = (ProductPager) fragmentManager.findFragmentByTag(ProductPager.class.getName());

            //解决重叠问题show里面可以指定恢复的页面
            fragmentManager.beginTransaction()
                    .show(homePager)
                    .hide(buyCoinPager)
                    .hide(winningPager)
                    .hide(showPager)
                    .hide(mePager)
                    .commit();

            //把当前显示的fragment记录下来
            currentFragment = homePager;

        } else { //正常启动时调用

            homePager = new ProductPager();
            buyCoinPager = new ProductPager();
            winningPager = new ProductPager();
            showPager = new ShowPager();
            mePager = new ProductPager();

            showFragment(homePager);
        }

        findView();
        setData();

        if (Utils.checkDeviceHasNavigationBar(MainActivity.this)) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, Utils.getNavigationBarHeight(MainActivity.this));
            rl_main.setLayoutParams(lp);
        }

    }

    //设置数据
    private void setData() {
        list = new ArrayList<>();
        list.add(homePager);
        list.add(buyCoinPager);
        list.add(winningPager);
        list.add(showPager);
        list.add(mePager);
    }

    //试图初始化 与设置监听
    private void findView() {
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rb_main_homepager = (RadioButton) findViewById(R.id.rb_main_homepager);
        rb_main_buycoins = (RadioButton) findViewById(R.id.rb_main_buycoins);
        rb_main_newresult = (RadioButton) findViewById(R.id.rb_main_newresult);
        rb_main_show = (RadioButton) findViewById(R.id.rb_main_show);
        rb_main_me = (RadioButton) findViewById(R.id.rb_main_me);
        rl_main = (RelativeLayout) findViewById(R.id.rl_main);

        rb_main_homepager.setOnClickListener(new MyOnclickListener());
        rb_main_buycoins.setOnClickListener(new MyOnclickListener());
        rb_main_newresult.setOnClickListener(new MyOnclickListener());
        rb_main_me.setOnClickListener(new MyOnclickListener());

        //设置监听
        rb_main_homepager.setOnClickListener(new MyOnclickListener());
        rb_main_buycoins.setOnClickListener(new MyOnclickListener());
        rb_main_newresult.setOnClickListener(new MyOnclickListener());
        rb_main_show.setOnClickListener(new MyOnclickListener());
        rb_main_me.setOnClickListener(new MyOnclickListener());

    }


    class MyOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rb_main_homepager:

                    id = 0;
                    showFragment(list.get(0));
                    rb_main_homepager.setChecked(true);
                    rb_main_buycoins.setChecked(false);
                    rb_main_newresult.setChecked(false);
                    rb_main_show.setChecked(false);
                    rb_main_me.setChecked(false);

                    break;
                case R.id.rb_main_buycoins:
                    id = 1;
                    showFragment(list.get(1));
                    rb_main_homepager.setChecked(false);
                    rb_main_buycoins.setChecked(true);
                    rb_main_newresult.setChecked(false);
                    rb_main_show.setChecked(false);
                    rb_main_me.setChecked(false);
                    break;
                case R.id.rb_main_newresult:
                    id = 2;
                    showFragment(list.get(2));
                    rb_main_homepager.setChecked(false);
                    rb_main_buycoins.setChecked(false);
                    rb_main_newresult.setChecked(true);
                    rb_main_show.setChecked(false);
                    rb_main_me.setChecked(false);
                    break;
                case R.id.rb_main_show:
                    id = 3;
                    showFragment(list.get(3));
                    rb_main_homepager.setChecked(false);
                    rb_main_buycoins.setChecked(false);
                    rb_main_newresult.setChecked(false);
                    rb_main_show.setChecked(true);
                    rb_main_me.setChecked(false);
                    break;
                case R.id.rb_main_me:

                    //判断是否登陆  未登陆  先登录  登陆 进入me页面
                    showFragment(list.get(4));
                    rb_main_homepager.setChecked(false);
                    rb_main_buycoins.setChecked(false);
                    rb_main_newresult.setChecked(false);
                    rb_main_me.setChecked(true);
                    id = 4;
                    break;
            }
        }
    }



    private Fragment currentFragment;
    private boolean flag = true;

    private void showFragment(Fragment fg) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (flag) {
            transaction.add(R.id.fl_main, fg);
            flag = false;
        } else {
            //如果之前没有添加过
            if (!fg.isAdded()) {
                transaction
                        .hide(currentFragment)
                        .add(R.id.fl_main, fg, fg.getClass().getName());
            } else {
                transaction.hide(currentFragment).show(fg);
            }
        }

        //全局变量，记录当前显示的fragment
        currentFragment = fg;
        boolean flag = this.isDestroyed();
        if (!flag) {
            transaction.commitAllowingStateLoss();
        }

    }



    private void selectPager() {
        if (id == 0) {
            showFragment(list.get(0));
            rb_main_homepager.setChecked(true);
            rb_main_buycoins.setChecked(false);
            rb_main_newresult.setChecked(false);
            rb_main_show.setChecked(false);
            rb_main_me.setChecked(false);
        } else if (id == 1) {
            showFragment(list.get(1));
            rb_main_homepager.setChecked(false);
            rb_main_buycoins.setChecked(true);
            rb_main_newresult.setChecked(false);
            rb_main_show.setChecked(false);
            rb_main_me.setChecked(false);
        } else if (id == 2) {
            showFragment(list.get(2));
            rb_main_homepager.setChecked(false);
            rb_main_buycoins.setChecked(false);
            rb_main_newresult.setChecked(true);
            rb_main_show.setChecked(false);
            rb_main_me.setChecked(false);
        } else if (id == 3) {
            showFragment(list.get(3));
            rb_main_homepager.setChecked(false);
            rb_main_buycoins.setChecked(false);
            rb_main_newresult.setChecked(false);
            rb_main_show.setChecked(true);
            rb_main_me.setChecked(false);
        } else if (id == 4) {
            showFragment(list.get(4));
            rb_main_homepager.setChecked(false);
            rb_main_buycoins.setChecked(false);
            rb_main_newresult.setChecked(false);
            rb_main_show.setChecked(false);
            rb_main_me.setChecked(true);
        }
    }


    long mExitTime;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, MainActivity.this.getString(R.string.Clickexit), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }

        return super.onKeyUp(keyCode, event);
    }


}