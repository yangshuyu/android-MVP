package net.iwantbuyer.module.product.model.impl;

import android.util.Log;

import net.iwantbuyer.api.RetrofitUtils;
import net.iwantbuyer.module.product.model.IProductModel;
import net.iwantbuyer.module.product.model.entity.BroadcastBean;
import net.iwantbuyer.module.product.model.entity.CarouselBean;
import net.iwantbuyer.module.product.model.entity.GameProductBean;
import net.iwantbuyer.module.product.presenter.IProductListener;

import java.io.IOException;
import java.util.List;

import retrofit2.HttpException;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangshuyu on 2017/5/3.
 */
public class ProductModelImpl implements IProductModel {

    RetrofitUtils retrofitUtils = RetrofitUtils.getInstance();

    @Override
    public void loadData(final IProductListener iProductListener) {

        retrofitUtils.getApi().getHomeCarousel()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<List<CarouselBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG_Carousel", e.toString());
                        iProductListener.failure();
                    }

                    @Override
                    public void onNext(List<CarouselBean> carouselBeen) {
                        iProductListener.getCarousel(carouselBeen);
                    }
                });

        retrofitUtils.getApi().getBroadcast()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BroadcastBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG_broadcast", e.toString());
                    }

                    @Override
                    public void onNext(List<BroadcastBean> broadcastBeen) {
                        Log.e("TAGbroad", broadcastBeen.size() + "");
                        iProductListener.getBroadcast(broadcastBeen);
                    }
                });

    }

    @Override
    public void loadProductData(final IProductListener iProductListener, final String order_by) {

        Log.e("TAG_order", order_by);
        retrofitUtils.getApi().get("1",order_by)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GameProductBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG_product", e.toString());
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            try {
                                String errorBody = httpException.response().errorBody().string();

                                int[] x = {0, 0, 0};
                                //TODO: parse To JSON Obj
                            } catch (IOException eee) {
                                eee.printStackTrace();
                            }
                        }
                        iProductListener.failureProduct();
                    }

                    @Override
                    public void onNext(List<GameProductBean> gameProductBeen) {
                        iProductListener.success(gameProductBeen,hasMoreData(retrofitUtils.getHeaders().get("link")),ProductModelImpl.this.page,order_by);
                    }
                });
    }

    @Override
    public void loadMoreData(final String page, final IProductListener iProductListener, final String order_by) {
        retrofitUtils.getApi().get(page,order_by)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GameProductBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG_product", e.toString());
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            try {
                                String errorBody = httpException.response().errorBody().string();

                                int[] x = {0, 0, 0};
                                //TODO: parse To JSON Obj
                            } catch (IOException eee) {
                                eee.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onNext(List<GameProductBean> gameProductBeen) {
                        iProductListener.success(gameProductBeen,hasMoreData(retrofitUtils.getHeaders().get("link")), ProductModelImpl.this.page,order_by);
                    }
                });
    }


    String page  = "1";

    private boolean hasMoreData(String link) {
        String next = "";
        String last = null;
        String[] str = link.split(",");
        for (int i = 0; i < str.length; i++) {
            if (str[i].contains("rel=\"next\"")) {
                next = str[i].substring(str[i].indexOf("<") + 1, str[i].indexOf(">"));
            }
            if (str[i].contains("rel=\"last\"")) {
                last = str[i].substring(str[i].indexOf("<") + 1, str[i].indexOf(">"));
            }
        }


        String pager = "";
        if (!"".equals(next) && !next.equals(last)) {
            String[] string = next.split("per_page");
            for (int i =0;i < string.length;i++){
                if(string[i].contains("page")) {
                    pager = string[i];
                }
            }

            str = pager.split("page=");
            if (str[1].length() >= 4 && isNumeric(str[1].substring(0, 3))) {
                page = str[1].substring(0, 2);
            }else if( str[1].length() >= 3 && isNumeric(str[1].substring(0, 2))) {
                page = str[1].substring(0, 1);
            }else if( str[1].length() >= 2 && isNumeric(str[1].substring(0, 1))) {
                page = str[1].substring(0, 1);
            }else {
                page = str[1];
            }
            return true;
        } else
            return false;

    }

    public boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
