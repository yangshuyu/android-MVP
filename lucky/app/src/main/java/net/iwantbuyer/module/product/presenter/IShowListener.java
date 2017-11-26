package net.iwantbuyer.module.product.presenter;

import net.iwantbuyer.module.product.model.entity.ShowBean;

import java.util.List;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public interface IShowListener {
    void hasPost(List<ShowBean> list);
}
