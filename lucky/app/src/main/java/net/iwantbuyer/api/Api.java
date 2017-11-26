package net.iwantbuyer.api;

import com.google.gson.JsonArray;


import net.iwantbuyer.module.product.model.entity.BroadcastBean;
import net.iwantbuyer.module.product.model.entity.CarouselBean;
import net.iwantbuyer.module.product.model.entity.GameProductBean;
import net.iwantbuyer.module.product.model.entity.ShowBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yangshuyu on 2017/5/3.
 */
public interface Api {

    /**
     * 首页产品
     * @return
     */
    @GET("/v1/games/?status=running&per_page=4&timezone=utc")
    Observable<List<GameProductBean>> get(
            @Query("page") String page,
            @Query("order_by") String order_by
    );
    /**
     * 首页产品轮播
     */

    @GET("/v1/banners/?per_page=20&page=1&timezone=utc")
    Observable<List<CarouselBean>> getHomeCarousel();

    /**
     * 首页广播
     * @return
     */
    @GET("/v1/broadcasts/?timezone=utc&lang=ar")
    Observable<List<BroadcastBean>> getBroadcast();


    /**
     * show 页面
     * @param page
     * @return
     */
    @GET("/v1/posts/?per_page=20&timezone=utc&lang=ar")
    Observable<List<ShowBean>> getShowPosts(
            @Query("page")  String page
    );

}
