package net.iwantbuyer.module.product.presenter;

import net.iwantbuyer.module.product.model.entity.BroadcastBean;
import net.iwantbuyer.module.product.model.entity.CarouselBean;
import net.iwantbuyer.module.product.model.entity.GameProductBean;

import java.util.List;

/**
 * Created by yangshuyu on 2017/4/28.
 */
public interface IProductListener {
    void success(List<GameProductBean> list,boolean hasMoreData,String link,String order_by);
    void getCarousel(List<CarouselBean> list);
    void getBroadcast(List<BroadcastBean> list);
    void failure();
    void failureProduct();
}

