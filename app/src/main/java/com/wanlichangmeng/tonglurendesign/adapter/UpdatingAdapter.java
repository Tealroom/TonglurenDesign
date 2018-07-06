package com.wanlichangmeng.tonglurendesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.activity.UpdatingActivity;
import com.wanlichangmeng.tonglurendesign.data.Updating;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdatingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List<Updating> data;

    private UpdatingAdapter.OnItemClickListener onItemClickListener;

    public UpdatingAdapter(Context context, List<Updating> data) {
        this.context = context;
        if (data == null) {
            this.data = new ArrayList();
        } else {
            this.data = data;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_updating_tab_recycler_head, parent, false);
            return new UpdatingAdapter.HeadViewHolder(view);
        } else if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_updating_tab_recycler_item, parent, false);
            return new UpdatingAdapter.ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_updating_tab_recycler_foot, parent, false);
            return new UpdatingAdapter.FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UpdatingAdapter.ItemViewHolder) {
            Glide.with(context)
                    .load(data.get(position).getAvatar()).apply(new RequestOptions().placeholder(R.color.colorAccent))

                    .into(((UpdatingAdapter.ItemViewHolder) holder).avatar);
            Glide.with(context).load(data.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            ((UpdatingAdapter.ItemViewHolder) holder).fragment_updating_tab1_itm_3.setBackgroundDrawable(resource);
                        }
                    });
            ((UpdatingAdapter.ItemViewHolder) holder).username.setText(data.get(position).getUsername());
            ((ItemViewHolder) holder).fragment_updating_tab1_itm_2.setText("我就是那条动态你服不服？");
            ((ItemViewHolder) holder).fragment_updating_tab1_itm_2.setOnClickListener(new View.OnClickListener(){
                int i = 0;
                Bundle bundle = new Bundle();
                public void onClick(View v) {
                    bundle.putString("new_or_content","content");
                    Intent intent = new Intent(context, UpdatingActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setOnItemClickListener(UpdatingAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.avatar)
        protected ImageView avatar;
        @BindView(R.id.fragment_updating_tab1_itm_2)
        protected TextView fragment_updating_tab1_itm_2;
        @BindView(R.id.fragment_updating_tab1_itm_3)
        protected ImageView fragment_updating_tab1_itm_3;
        @BindView(R.id.username)
        protected TextView username;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
