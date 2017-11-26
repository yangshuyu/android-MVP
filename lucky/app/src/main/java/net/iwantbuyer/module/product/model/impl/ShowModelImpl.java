package net.iwantbuyer.module.product.model.impl;

import android.util.Log;

import net.iwantbuyer.api.RetrofitUtils;
import net.iwantbuyer.module.product.model.IShowModel;
import net.iwantbuyer.module.product.model.entity.ShowBean;
import net.iwantbuyer.module.product.presenter.IShowListener;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public class ShowModelImpl implements IShowModel {

    RetrofitUtils retrofitUtils = RetrofitUtils.getInstance();
    @Override
    public void loadData(final IShowListener iShowListener) {
        retrofitUtils.getApi().getShowPosts("1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ShowBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG_posts", e.toString());
                    }

                    @Override
                    public void onNext(List<ShowBean> showBeen) {
                        Log.e("TAG_posts", showBeen.size() + "");
                        iShowListener.hasPost(showBeen);
                    }
                });

    }
}
