package net.iwantbuyer.module.product.presenter.Impl;

import net.iwantbuyer.base.BasePresenter;
import net.iwantbuyer.module.product.model.IShowModel;
import net.iwantbuyer.module.product.model.entity.ShowBean;
import net.iwantbuyer.module.product.model.impl.ShowModelImpl;
import net.iwantbuyer.module.product.presenter.IShowListener;
import net.iwantbuyer.module.product.view.IShowView;

import java.util.List;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public class ShowPresenter extends BasePresenter<IShowView> implements IShowListener{
    IShowModel showModel;
    public ShowPresenter(){
        showModel = new ShowModelImpl();
    }

    public void getData(){
        showModel.loadData(this);
    }
    @Override
    public void hasPost(List<ShowBean> list) {
        mView.setShow(list);
    }

}
