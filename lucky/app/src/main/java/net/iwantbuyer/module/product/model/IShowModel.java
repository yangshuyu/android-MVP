package net.iwantbuyer.module.product.model;

import net.iwantbuyer.module.product.presenter.IShowListener;
import net.iwantbuyer.module.product.view.IShowView;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public interface IShowModel {
    void loadData(IShowListener iShowListener);
}
