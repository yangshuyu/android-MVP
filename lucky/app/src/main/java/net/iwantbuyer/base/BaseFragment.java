package net.iwantbuyer.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.iwantbuyer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangshuyu on 2016/5/31.
 * 碎片基类
 */
public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment implements View.OnClickListener{

    /**
     * 注意，资源的ID一定要一样
     */
    @Nullable
    @BindView(R.id.network_loading)
    LinearLayout network_loading;


    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;

    public T presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        presenter.attach((V)this);
    }

    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setView();
        initData();
    }


    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
    protected abstract int attachLayoutRes();



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    public abstract T initPresenter();

    public abstract void initData();
    public abstract void setView();

    public abstract void onClick(int id);

    @Override
    public void onClick(View v) {
        onClick(v.getId());
    }
}
