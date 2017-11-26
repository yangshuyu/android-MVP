package net.iwantbuyer.module.product.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import net.iwantbuyer.R;
import net.iwantbuyer.base.BaseAdapter;
import net.iwantbuyer.module.product.model.entity.GameProductBean;
import net.iwantbuyer.utils.Utils;

import java.util.List;


/**
 * Created by yangshuyu on 2017/5/5.
 */
public class ProductAdapter extends BaseAdapter<GameProductBean> {

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recycleview_product, null);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void convert(final BaseHolder holder, int position) {
        final Holder hold;
        if(holder instanceof Holder) {
            hold = (Holder) holder;
        }else {
            return;
        }
        if(position >= data.size()) {
            return;
        }
        int precent = (int) ((data.get(position).getShares()-data.get(position).getLeft_shares())*100/data.get(position).getShares());
        hold.tv_producet_discribe.setText(data.get(position).getProduct().getTitle());
        hold.tv_product_count.setText("" + data.get(position).getShares());
        hold.pb_product_progress.setProgress((int) ((data.get(position).getShares() - data.get(position).getLeft_shares()) * 100 / data.get(position).getShares()));
        hold.tv_home_percentage.setText(precent+"%");
        Glide.with(context).load("http:" + data.get(position).getProduct().getTitle_image()).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                hold.iv_product_icon.setImageBitmap(resource);
            }
        });

        if(data.get(position).getShares_increment() == 5) {
            if(Utils.getSpData("service",context)!= null && Utils.getSpData("service",context).contains("api-my")) {
                if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("zh")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_zh_5);
                }else if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("ms")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_ms_5);
                }else {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_5);
                }
            }else {
                if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("zh")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_zh_10);
                }else if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("ms")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_ms_10);
                }else {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_10);
                }
            }
        }else if(data.get(position).getShares_increment() == 10) {
            if(Utils.getSpData("service",context)!= null && Utils.getSpData("service",context).contains("api-my")) {
                if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("zh")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_zh_10);
                }else if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("ms")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_ms_10);
                }else {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_10);
                }
            }else {
                if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("zh")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_zh_10);
                }else if(Utils.getSpData("locale",context) !=null && Utils.getSpData("locale",context).contains("ms")) {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_ms_10);
                }else {
                    hold.iv_product_increment.setBackgroundResource(R.drawable.homepager_my_10);
                }
            }
        }
    }

    public ProductAdapter(Context context, List<GameProductBean> data) {
        super(context, data);
    }



    class Holder extends BaseAdapter.BaseHolder{

        private TextView tv_producet_discribe;
        private TextView tv_product_count;
        private ProgressBar pb_product_progress;
        private ImageView iv_product_icon;
        private TextView tv_home_percentage;
        private View view;
        private ImageView iv_product_increment;
        public Holder(View view) {
            super(view);
            this.view = view;
            tv_producet_discribe = (TextView) view.findViewById(R.id.tv_producet_discribe);
            tv_product_count = (TextView) view.findViewById(R.id.tv_product_count);
            pb_product_progress = (ProgressBar) view.findViewById(R.id.pb_product_progress);
            iv_product_icon = (ImageView) view.findViewById(R.id.iv_product_icon);
            tv_home_percentage = (TextView) view.findViewById(R.id.tv_home_percentage);
            iv_product_increment = (ImageView) view.findViewById(R.id.iv_product_increment);
        }
    }
}
