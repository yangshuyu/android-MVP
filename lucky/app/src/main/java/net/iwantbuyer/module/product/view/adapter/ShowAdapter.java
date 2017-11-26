package net.iwantbuyer.module.product.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import net.iwantbuyer.R;
import net.iwantbuyer.base.BaseAdapter;
import net.iwantbuyer.module.product.model.entity.ShowBean;
import net.iwantbuyer.view.RoundCornerImageView;

import java.util.List;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public class ShowAdapter extends BaseAdapter<ShowBean>{

    public ShowAdapter(Context context, List<ShowBean> data) {
        super(context, data);
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show, null);
        ShowHolder holder = new ShowHolder(view);
        return holder;
    }

    @Override
    protected void convert(BaseHolder holder, int position) {
        Log.e("TAG_show", "" + position);
        final ShowHolder hold;
        if(holder instanceof ShowHolder) {
            hold = (ShowHolder) holder;
        }else {
            return;
        }
        if(position >= data.size()) {
            return;
        }

        Glide.with(context).load(data.get(position).getUser().getProfile().getPicture()).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                hold.riv_show_head.setImageBitmap(resource);
            }
        });
        hold.tv_show_name.setText(data.get(position).getUser().getProfile().getName() + "");
        hold.tv_show_time.setText(data.get(position).getCreated_at().substring(0,19).replace("T"," ") + "");
        hold.tv_show_title.setText(data.get(position).getContent());
        hold.tv_show_information.setText(context.getString(R.string.productinformation_) + data.get(position).getProduct_title() + "");
        hold.tv_show_round.setText(data.get(position).getGame_issue_id() + "");
        if(data.get(position).getImages().size() ==1 ) {
            Glide.with(context).load("https:"+data.get(position).getImages().get(0)).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    setImage(hold.iv_show_one, resource);
                }
            });
        }

        hold.iv_show_two.setVisibility(View.GONE);
        hold.iv_show_three.setVisibility(View.GONE);
        if(data.get(position).getImages().size() ==2) {
            hold.iv_show_two.setVisibility(View.VISIBLE);
            Glide.with(context).load("https:"+data.get(position).getImages().get(0)).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    setImage(hold.iv_show_one, resource);
                }
            });
            Glide.with(context).load("https:"+data.get(position).getImages().get(1)).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    setImage(hold.iv_show_two, resource);
                }
            });
        }
        if(data.get(position).getImages().size() == 3) {
            hold.iv_show_two.setVisibility(View.VISIBLE);
            hold.iv_show_three.setVisibility(View.VISIBLE);
            Glide.with(context).load("https:"+data.get(position).getImages().get(0)).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    setImage(hold.iv_show_one, resource);
                }
            });
            Glide.with(context).load("https:"+data.get(position).getImages().get(1)).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    setImage(hold.iv_show_two, resource);
                }
            });
            Glide.with(context).load("https:"+data.get(position).getImages().get(2)).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    setImage(hold.iv_show_three, resource);
                }
            });
        }


//        hold.iv_show_one.setOnClickListener(new MyOnClickListener(position));
//        hold.iv_show_two.setOnClickListener(new MyOnClickListener(position));
//        hold.iv_show_three.setOnClickListener(new MyOnClickListener(position));
    }

    class ShowHolder extends BaseHolder{
        private RoundCornerImageView riv_show_head;
        private TextView tv_show_name;
        private TextView tv_show_time;
        private TextView tv_show_title;
        private ImageView iv_show_one;
        private ImageView iv_show_two;
        private ImageView iv_show_three;
        private TextView tv_show_information;
        private TextView tv_show_round;
        public ShowHolder(View itemView) {
            super(itemView);
            riv_show_head = (RoundCornerImageView) itemView.findViewById(R.id.riv_show_head);
            tv_show_name = (TextView) itemView.findViewById(R.id.tv_show_name);
            tv_show_time = (TextView) itemView.findViewById(R.id.tv_show_time);
            tv_show_title = (TextView) itemView.findViewById(R.id.tv_show_title);
            iv_show_one = (ImageView) itemView.findViewById(R.id.iv_show_one);
            iv_show_two = (ImageView) itemView.findViewById(R.id.iv_show_two);
            iv_show_three = (ImageView) itemView.findViewById(R.id.iv_show_three);
            tv_show_information = (TextView) itemView.findViewById(R.id.tv_show_information);
            tv_show_round = (TextView) itemView.findViewById(R.id.tv_show_round);
        }
    }

    private void setImage(ImageView imageView, Bitmap bitmap) {
        int picWidth = bitmap.getWidth();
        int picHeight = bitmap.getHeight();
        // 从源图片中截取需要的部分
        if (picWidth > picHeight) {
            bitmap = Bitmap.createBitmap(bitmap, (picWidth - picHeight) / 2, 0, picHeight, picHeight);
        } else {
            bitmap = Bitmap.createBitmap(bitmap, 0, (picHeight - picWidth) / 2, picWidth, picWidth);
        }

//        BitmapDrawable bitmapD = new BitmapDrawable(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
