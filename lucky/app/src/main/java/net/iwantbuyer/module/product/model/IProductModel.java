package net.iwantbuyer.module.product.model;

import net.iwantbuyer.module.product.presenter.IProductListener;

/**
 * Created by yangshuyu on 2017/5/3.
 */
public interface IProductModel {
    void loadData(IProductListener iProductListener);
    void loadMoreData(String page , IProductListener iProductListener,String order_by);
    void loadProductData(IProductListener iProductListener,String order_by);
}
