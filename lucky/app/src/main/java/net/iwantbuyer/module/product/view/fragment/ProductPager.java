package net.iwantbuyer.module.product.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.iwantbuyer.R;
import net.iwantbuyer.base.BaseFragment;
import net.iwantbuyer.home.MainActivity;
import net.iwantbuyer.module.product.model.entity.BroadcastBean;
import net.iwantbuyer.module.product.model.entity.CarouselBean;
import net.iwantbuyer.module.product.model.entity.GameProductBean;
import net.iwantbuyer.module.product.presenter.Impl.ProductPresenter;
import net.iwantbuyer.module.product.view.IProductView;
import net.iwantbuyer.module.product.view.adapter.HomeImagePageAdapter;
import net.iwantbuyer.module.product.view.adapter.ProductAdapter;
import net.iwantbuyer.utils.DensityUtil;
import net.iwantbuyer.view.AutoTextHomeView;
import net.iwantbuyer.view.BottomScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by yangshuyu on 2017/5/2.
 */
public class ProductPager extends BaseFragment<IProductView, ProductPresenter> implements IProductView {

    @BindView(R.id.rl_home_header)
    RelativeLayout rl_home_header;

    @BindView(R.id.vp_home)                    //轮播图
            ViewPager vp_home;

    @BindView(R.id.ll_home_point)
    LinearLayout ll_home_point;       //轮播图上面的标记点

    @BindView(R.id.rv_home_producer)
    RecyclerView rv_home_producer;    //产品

    @BindView(R.id.atv_home_marquee)
    AutoTextHomeView atv_home_marquee;    //跑马灯

    @BindView(R.id.srl_home_refresh)
    SwipeRefreshLayout srl_home_refresh;

    @BindView(R.id.tv_net_again)
    TextView tv_net_again;

    @BindView(R.id.sv_home)
    BottomScrollView sv_home;

    @BindView(R.id.tl_home_products)
    TabLayout tl_home_products;

    //上拉加载更
    @BindView(R.id.ll_home_loading)
    LinearLayout ll_home_loading;
    @BindView(R.id.pb_loading_data)
    ProgressBar pb_loading_data;
    @BindView(R.id.tv_loading_data)
    TextView tv_loading_data;


    //网络连接错误 与没有数据
    @BindView(R.id.rl_keepout)
    RelativeLayout rl_keepout;
    @BindView(R.id.rl_neterror)
    RelativeLayout rl_neterror;
    @BindView(R.id.rl_nodata)
    RelativeLayout rl_nodata;
    @BindView(R.id.rl_loading)
    RelativeLayout rl_loading;

    //网络连接错误 与没有数据
    @BindView(R.id.rl_list_keepout)
    RelativeLayout rl_list_keepout;
    @BindView(R.id.rl_list_neterror)
    RelativeLayout rl_list_neterror;
    @BindView(R.id.rl_list_nodata)
    RelativeLayout rl_list_nodata;
    @BindView(R.id.rl_list_loading)
    RelativeLayout rl_list_loading;
    @BindView(R.id.tv_list_net_again)
    TextView tv_list_net_again;

    List<GameProductBean> list = new ArrayList<>();
    boolean isNeesPull;

    @Override
    protected int attachLayoutRes() {
        return R.layout.pager_home;
    }


    @Override
    public ProductPresenter initPresenter() {
        return new ProductPresenter();
    }

    @Override
    public void initData() {
        presenter.getData();
        presenter.getProductData("all");


    }

    @Override
    public void setView() {
        final String[] order_by = {"all"};
        tl_home_products.addTab(tl_home_products.newTab().setText(context.getString(R.string.All)), 0);
        tl_home_products.addTab(tl_home_products.newTab().setText(context.getString(R.string.progress)), 1);
        tl_home_products.addTab(tl_home_products.newTab().setText(context.getString(R.string.New)), 2);
        tl_home_products.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        //请求ALL
                        order_by[0] = "all";
                        presenter.getProductData(order_by[0]);
                        break;
                    case 1:
                        //请求Progress
                        order_by[0] = "progress";
                        presenter.getProductData(order_by[0]);
                        Log.e("TAGnew",order_by[0] + "");
                        break;
                    case 2:
                        //New
                        order_by[0] = "new";
                        presenter.getProductData(order_by[0]);
                        Log.e("TAGnew",order_by[0] + "");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        srl_home_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
                presenter.getProductData(order_by[0]);
                Log.e("TAGnew",order_by[0] + "");
            }
        });
    }

    @Override
    public void onClick(int id) {

    }

    @Override
    public void showLoading() {
        rl_keepout.setVisibility(View.VISIBLE);
        rl_neterror.setVisibility(View.GONE);
        rl_nodata.setVisibility(View.GONE);
        rl_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rl_keepout.setVisibility(View.GONE);
    }

    @Override
    public void showNetError() {
        rl_keepout.setVisibility(View.VISIBLE);
        rl_neterror.setVisibility(View.VISIBLE);
        rl_nodata.setVisibility(View.GONE);
        rl_loading.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {
        rl_keepout.setVisibility(View.VISIBLE);
        rl_neterror.setVisibility(View.GONE);
        rl_nodata.setVisibility(View.VISIBLE);
        rl_loading.setVisibility(View.GONE);
    }

    @Override
    public void showListLoading() {
        rl_list_keepout.setVisibility(View.VISIBLE);
        rl_list_neterror.setVisibility(View.GONE);
        rl_list_nodata.setVisibility(View.GONE);
        rl_list_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListNetError() {
        rl_list_keepout.setVisibility(View.VISIBLE);
        rl_list_neterror.setVisibility(View.VISIBLE);
        rl_list_nodata.setVisibility(View.GONE);
        rl_list_loading.setVisibility(View.GONE);

        rl_list_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_list_loading.setEnabled(false);

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

                rl_list_loading.setEnabled(true);
            }
        });
    }

    @Override
    public void hideListLoading() {
        rl_list_keepout.setVisibility(View.GONE);
    }

    @Override
    public void setProduct(List<GameProductBean> list, final boolean isMoreData, final String page, final String order_by) {
        int pager = Integer.parseInt(page);
        if (pager > 2) {
            this.list.addAll(list);
        } else {
            this.list = list;
        }
        isNeesPull = true;
        final ProductAdapter productAdapter = new ProductAdapter(context, this.list);
        rv_home_producer.setAdapter(productAdapter);
        rv_home_producer.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        sv_home.setOnScrollToBottomLintener(new BottomScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isNeesPull && isBottom && isMoreData) {
                    presenter.getMoreData(page,order_by);
                    isNeesPull = false;
                    ll_home_loading.setVisibility(View.VISIBLE);
                    pb_loading_data.setVisibility(View.VISIBLE);
                    tv_loading_data.setText(context.getString(R.string.loading___));

                } else if (isNeesPull && isBottom && !isMoreData) {
                    ll_home_loading.setVisibility(View.VISIBLE);
                    pb_loading_data.setVisibility(View.GONE);
                    tv_loading_data.setText(context.getString(R.string.nomoreproduct));
                    Observable.interval(3, TimeUnit.SECONDS)
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Long>() {
                                @Override
                                public void call(Long aLong) {
                                    ll_home_loading.setVisibility(View.GONE);
                                }
                            });
                }

            }
        });

    }

    @Override
    public void setCarousel(final List<CarouselBean> list) {
        srl_home_refresh.setRefreshing(false);
        vp_home.setAdapter(new HomeImagePageAdapter(context, list));
        ll_home_point.removeAllViews();
        //轮播图下面的表
        Observable.from(list)
                .map(new Func1<CarouselBean, ImageView>() {
                    @Override
                    public ImageView call(CarouselBean carouselBean) {
                        return new ImageView(context);
                    }
                })
                .subscribe(new Action1<ImageView>() {
                    @Override
                    public void call(ImageView imageView) {
                        imageView.setBackgroundResource(R.drawable.homepager_point_selector);
                        ll_home_point.addView(imageView);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        lp.gravity = Gravity.CENTER;
                        lp.leftMargin = DensityUtil.px2dip(context, 20);
                        imageView.setLayoutParams(lp);
                        ll_home_point.getChildAt(0).setEnabled(false);
                    }
                });


        //开始轮播
        startViewPager();

        vp_home.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        srl_home_refresh.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                        srl_home_refresh.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        srl_home_refresh.setEnabled(true);
                        break;
                }
                return false;
            }
        });

        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int total = list.size();
                for (int i = 0; i < total; i++) {
                    if (i == position % total) {
                        ll_home_point.getChildAt(i).setEnabled(false);
                    } else {
                        ll_home_point.getChildAt(i).setEnabled(true);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    stopViewpager();
                } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                    stopViewpager();
                    startViewPager();
                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    stopViewpager();
                }
            }
        });

    }

    @Override
    public void setBroadcast(List<BroadcastBean> list) {
        SpannableStringBuilder builder = null;
        //需要变颜色的字符串
        //跑马灯数据
        final List<SpannableStringBuilder> mStringArray = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).getTemplate();
            //获取内容字符串
            String content = list.get(i).getContent();
            builder = new SpannableStringBuilder(content);
            ForegroundColorSpan redSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.ff9c05));
            ForegroundColorSpan blackSpan = null;
            builder.setSpan(redSpan, 0, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            String[] st = str.split("\\}");
            for (int j = 0; j < st.length; j++) {
                int begin = 0;
                int end = 0;
                if (st[j].contains("{")) {
                    String[] s = st[j].split("\\{");
                    //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
                    begin = content.indexOf(s[0]);
                    end = begin + s[0].length();

                }
                blackSpan = new ForegroundColorSpan(Color.BLACK);
                builder.setSpan(blackSpan, begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            mStringArray.add(builder);
        }

        atv_home_marquee.setText(mStringArray.get(0));
        final int[] loopCount = {1};
        Observable.interval(5,5,TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        int i = loopCount[0] % mStringArray.size();
                        atv_home_marquee.next();
                        atv_home_marquee.setText(mStringArray.get(i));
                        loopCount[0]++;
                    }
                });


    }



    Subscription mSubViewpager;

    /**
     * 开始轮播
     */
    public void startViewPager() {
        mSubViewpager = Observable.interval(5, 5, TimeUnit.SECONDS)  // 5s的延迟，5s的循环时间
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        int currentPager = vp_home.getCurrentItem();
                        vp_home.setCurrentItem(++currentPager);
                    }
                });
    }

    /**
     * 停止轮播
     */
    public void stopViewpager() {
        if (mSubViewpager != null) {
            mSubViewpager.unsubscribe();
            mSubViewpager = null;
        }
    }
}
