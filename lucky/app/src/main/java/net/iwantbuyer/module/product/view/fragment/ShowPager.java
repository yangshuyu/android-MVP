package net.iwantbuyer.module.product.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.iwantbuyer.R;
import net.iwantbuyer.base.BaseFragment;
import net.iwantbuyer.module.product.model.entity.ShowBean;
import net.iwantbuyer.module.product.presenter.Impl.ShowPresenter;
import net.iwantbuyer.module.product.view.IShowView;
import net.iwantbuyer.module.product.view.adapter.ShowAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public class ShowPager extends BaseFragment<IShowView,ShowPresenter> implements IShowView{

    @BindView(R.id.rv_show)
    RecyclerView rv_show;

    //上拉加载更
    @BindView(R.id.ll_loading_data)
    LinearLayout ll_home_loading;
    @BindView(R.id.pb_loading_data)
    ProgressBar pb_loading_data;
    @BindView(R.id.tv_loading_data)
    TextView tv_loading_data;


    //网络连接错误 与没有数据
    @BindView(R.id.rl_keepout)
    RelativeLayout rl_keepout;
    @BindView(R.id.rl_neterror)
    RelativeLayout rl_neterror;
    @BindView(R.id.rl_nodata)
    RelativeLayout rl_nodata;
    @BindView(R.id.rl_loading)
    RelativeLayout rl_loading;

    @Override
    protected int attachLayoutRes() {
        return R.layout.pager_show;
    }

    @Override
    public ShowPresenter initPresenter() {
        return new ShowPresenter();
    }

    @Override
    public void initData() {
        presenter.getData();
        Toast.makeText(context,"这就是一个toast",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setView() {

    }

    @Override
    public void onClick(int id) {

    }


    @Override
    public void setShow(List<ShowBean> list) {
        ShowAdapter showAdapter = new ShowAdapter(context,list);
        rv_show.setAdapter(showAdapter);
        rv_show.setLayoutManager(new GridLayoutManager(context,1));
    }
}
