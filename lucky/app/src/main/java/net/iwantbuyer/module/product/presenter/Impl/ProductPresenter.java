package net.iwantbuyer.module.product.presenter.Impl;

import net.iwantbuyer.base.BasePresenter;
import net.iwantbuyer.module.product.model.IProductModel;
import net.iwantbuyer.module.product.model.entity.BroadcastBean;
import net.iwantbuyer.module.product.model.entity.CarouselBean;
import net.iwantbuyer.module.product.model.entity.GameProductBean;
import net.iwantbuyer.module.product.model.impl.ProductModelImpl;
import net.iwantbuyer.module.product.presenter.IProductListener;
import net.iwantbuyer.module.product.view.IProductView;

import java.util.List;

/**
 * Created by yangshuyu on 2017/5/3.
 */
public class ProductPresenter extends BasePresenter<IProductView> implements IProductListener {
    IProductModel IProductModel;
    boolean isFirst = true;

    public ProductPresenter(){
        IProductModel = new ProductModelImpl();
    }

    public void getData(){
        if(isFirst) {
            mView.showLoading();
            isFirst = false;
        }

        IProductModel.loadData(this);
    }
    public void getMoreData(String page,String order_by){
        IProductModel.loadMoreData(page,this,order_by);
    }

    public void getProductData(String order_by){
        IProductModel.loadProductData(this,order_by);
    }
    @Override
    public void success(List<GameProductBean> list,boolean isMoreData,String link,String order_by) {
        mView.setProduct(list,isMoreData,link,order_by);
        mView.hideListLoading();
    }

    @Override
    public void getCarousel(List<CarouselBean> list) {
        mView.hideLoading();
        mView.setCarousel(list);
    }

    @Override
    public void getBroadcast(List<BroadcastBean> list) {
        mView.setBroadcast(list);
    }

    @Override
    public void failure() {
        mView.showNetError();
    }

    @Override
    public void failureProduct() {
        mView.showListNetError();
    }
}
