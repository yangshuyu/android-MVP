package net.iwantbuyer.module.product.view;

import net.iwantbuyer.base.IBaseView;
import net.iwantbuyer.module.product.model.entity.BroadcastBean;
import net.iwantbuyer.module.product.model.entity.CarouselBean;
import net.iwantbuyer.module.product.model.entity.GameProductBean;

import java.util.List;

/**
 * Created by yangshuyu on 2017/5/2.
 */
public interface IProductView extends IBaseView {
    void setProduct(List<GameProductBean> list,boolean isMoreData,String page,String order_by);
    void setCarousel(List<CarouselBean> list);
    void setBroadcast(List<BroadcastBean> list);
    void showListLoading();
    void showListNetError();
    void hideListLoading();
}
