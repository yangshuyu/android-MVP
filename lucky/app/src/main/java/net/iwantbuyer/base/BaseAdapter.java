package net.iwantbuyer.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangshuyu on 2016/4/21.
 * 适配器基类
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseHolder>{
    protected Context context;
    protected List<T> data;
    private View mParentView;
    // head and footer
    private View mHeaderView;
    private View mFooterView;



    public BaseAdapter(Context context) {
        this(context, null);
    }

    public BaseAdapter(Context context, List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.context = context;
    }





    @Override
    public int getItemCount() {
        int count = data.size() + getHeaderViewsCount() + getFooterViewsCount();
        return count;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BaseAdapter.BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(final BaseAdapter.BaseHolder holder, final int position) {
        convert((BaseHolder) holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null) {
                    onClickListener.onclick(holder.itemView,position);
                }
            }
        });


    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param holder A fully initialized helper.
     * @param position   The item that needs to be displayed.
     */
    protected abstract void convert(BaseHolder holder, int position);


    /************************************* 头尾视图 ****************************************/

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addHeaderView(View headerView) {
        headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mHeaderView = headerView;
        notifyDataSetChanged();
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void addFooterView(View footerView) {
        footerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mFooterView = footerView;
        notifyDataSetChanged();
    }

    public int getHeaderViewsCount() {
        return mHeaderView == null ? 0 : 1;
    }

    public int getFooterViewsCount() {
        return mFooterView == null ? 0 : 1;
    }

    /************************************数据操作****************************************/

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    public T getItem(int position) {
        return data.get(position);
    }

    /**
     * Get the data of list
     *
     * @return
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 更新数据，替换原有数据
     *
     * @param items
     */
    public void updateItems(List<T> items) {
        data = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    private void _addItem(int position, T item) {
        if (data == null || data.size() == 0) {
            data = new ArrayList<>();
            data.add(item);
        } else {
            data.add(position, item);
        }
    }

    private void _addItemList(int position, List<T> items) {
        if (data == null || data.size() == 0) {
            data = new ArrayList<>();
        }
        data.addAll(position, items);
    }

    /**
     * 首部插入一条数据
     *
     * @param item 数据
     */
    public void addItem(T item) {
        _addItem(0, item);
        notifyItemInserted(0);
    }

    /**
     * 插入一条数据
     *
     * @param item     数据
     * @param position 插入位置
     */
    public void addItem(T item, int position) {
        position = Math.min(position, data.size());
        _addItem(position, item);
        notifyItemInserted(_calcPosition(position));
    }

    /**
     * 尾部插入一条数据
     * @param item 数据
     */
    public void addLastItem(T item) {
        _addItem(data.size(), item);
        notifyItemInserted(_calcPosition(data.size()));
    }

    /**
     * 在列表尾添加一串数据
     *
     * @param items
     */
    public void addItems(List<T> items) {
        _addItemList(data.size(), items);
        int position = _calcPosition(data.size());
        for (T item : items) {
            notifyItemInserted(position++);
        }
    }

    /**
     * 在列表尾添加一串数据
     *
     * @param items
     */
    public void addItems(List<T> items, int position) {
        position = Math.min(position, data.size());
        _addItemList(position, items);
        int pos = _calcPosition(position);
        for (T item : items) {
            notifyItemInserted(pos++);
        }
    }

    /**
     * 移除一条数据
     *
     * @param position 位置
     */
    public void removeItem(int position) {
        if (position > data.size() - 1) {
            return;
        }
        int pos = _calcPosition(position);
        data.remove(position);
        notifyItemRemoved(pos);
    }

    /**
     * 移除一条数据
     *
     * @param item 数据
     */
    public void removeItem(T item) {
        int pos = 0;
        for (T info : data) {
            if (item.hashCode() == info.hashCode()) {
                removeItem(pos);
                break;
            }
            pos++;
        }
    }

    /**
     * 清除所有数据
     */
    public void cleanItems() {
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * 计算位置，算上头部
     * @param position
     * @return
     */
    private int _calcPosition(int position) {
        if (mHeaderView != null) {
            position++;
        }
        return position;
    }


    /************************************监听****************************************/

    public interface OnClickListener {
        void onclick(View view, int position);

    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class BaseHolder extends RecyclerView.ViewHolder{

        public BaseHolder(View itemView) {
            super(itemView);
        }
        public BaseHolder(View itemView,int viewType) {
            super(itemView);
        }
    }
}
