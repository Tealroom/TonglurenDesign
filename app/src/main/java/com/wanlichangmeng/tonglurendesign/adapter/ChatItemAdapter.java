package com.wanlichangmeng.tonglurendesign.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wanlichangmeng.tonglurendesign.MyApplication;
import com.wanlichangmeng.tonglurendesign.R;
import com.wanlichangmeng.tonglurendesign.utils.BGABadgeInit;
import com.wanlichangmeng.tonglurendesign.utils.Conversation;
import com.wanlichangmeng.tonglurendesign.utils.UserInfo;

import java.util.List;


import cn.bingoogolapple.badgeview.BGABadgeLinearLayout;
import cn.bingoogolapple.badgeview.BGABadgeRelativeLayout;
import cn.bingoogolapple.badgeview.annotation.BGABadge;

@BGABadge({
        View.class, // 对应 cn.bingoogolapple.badgeview.BGABadgeView，不想用这个类的话就删了这一行

        TextView.class, // 对应 cn.bingoogolapple.badgeview.BGABadgeFloatingTextView，不想用这个类的话就删了这一行
        RadioButton.class, // 对应 cn.bingoogolapple.badgeview.BGABadgeRadioButton，不想用这个类的话就删了这一行
        LinearLayout.class, // 对应 cn.bingoogolapple.badgeview.BGABadgeLinearLayout，不想用这个类的话就删了这一行
        FrameLayout.class, // 对应 cn.bingoogolapple.badgeview.BGABadgeFrameLayout，不想用这个类的话就删了这一行
        RelativeLayout.class, // 对应 cn.bingoogolapple.badgeview.BGABadgeRelativeLayout，不想用这个类的话就删了这一行
        FloatingActionButton.class, // 对应 cn.bingoogolapple.badgeview.BGABadgeFloatingActionButton，不想用这个类的话就删了这一行

})

public class ChatItemAdapter extends RecyclerView.Adapter<ChatItemAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private List<Conversation> conversationList;

    private List<String> list;

    private List<UserInfo> userInfoList;



    public ChatItemAdapter(Context context, List<Conversation> conversationList, List<UserInfo> userInfoList) {
        this.inflater = LayoutInflater.from(context);
        this.conversationList = conversationList;
        this.userInfoList = userInfoList;
    }

//    @Override
//    public int getCount() {
//        return conversationList.size();
//    }
//
//    @Override
//    public Conversation getItem(int position) {
//        //String message = conversationList.get(position).getLastMessage();
//
//        return  conversationList.get(position);
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_message_chatitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
                    //对肖像是应该封装一下，需要圆图
            //viewHolder.portrait = (Portrait) convertView.findViewById(R.id.chat_item_portrait);
        viewHolder.portrait = (ImageView) convertView.findViewById(R.id.chat_item_portrait);
        viewHolder.name = (TextView) convertView.findViewById(R.id.chat_item_name);
        viewHolder.time = (TextView) convertView.findViewById(R.id.chat_item_time);
        viewHolder.content = (TextView) convertView.findViewById(R.id.chat_item_content);
            //viewHolder.messageNum =  convertView.findViewById(R.id.chat_item_message_num);
        viewHolder.chatItrm =  convertView.findViewById(R.id.chat_item_self);
        new BGABadgeInit();


        convertView.setTag(viewHolder);
        Log.e("tefhdsa",""+conversationList.size());

        //viewHolder.init(conversationList.get(position));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.init(conversationList.get(position));
    }



    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.item_fragment_message_chatitem, parent, false);
//
//            viewHolder = new ViewHolder();
//
//            //对肖像是应该封装一下，需要圆图
//            //viewHolder.portrait = (Portrait) convertView.findViewById(R.id.chat_item_portrait);
//            viewHolder.portrait = (ImageView) convertView.findViewById(R.id.chat_item_portrait);
//            viewHolder.name = (TextView) convertView.findViewById(R.id.chat_item_name);
//            viewHolder.time = (TextView) convertView.findViewById(R.id.chat_item_time);
//            viewHolder.content = (TextView) convertView.findViewById(R.id.chat_item_content);
//            //viewHolder.messageNum =  convertView.findViewById(R.id.chat_item_message_num);
//            viewHolder.chatItrm =  convertView.findViewById(R.id.chat_item_self);
//            new BGABadgeInit();
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.init(getItem(position));
//
//
//
//        return convertView;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView portrait;

        private TextView name;

        private TextView time;

        private TextView content;

        private TextView messageNum;

        private BGABadgeLinearLayout chatItrm;



        private void init(Conversation item) {
            String message = item.getMessage();
            UserInfo userInfo = item.getUserInfo();
            int unread = item.getUnreadNum();

            //portrait.setUserId(Integer.parseInt(userInfo.getId())).setUrl(userInfo.getIconurl()).show();
            //只有用全局
            Bitmap bitmap = null;
            RequestOptions options = new RequestOptions().circleCrop().override(100,100);
            String url = "http://img5.duitang.com/uploads/item/201609/26/20160926124027_vxRkt.jpeg";
            Glide.with(MyApplication.getInstance()).asBitmap().apply(options).load(url).into(portrait
            );
            name.setText("我是谁");
            time.setText("18-6-21");
            //messageNum.setText("hehe");
            content.setText(message);

            if (unread != 0) {
                //messageNum.showCirclePointBadge();
                //messageNum.showTextBadge(unread + "");
                chatItrm.showCirclePointBadge();
                chatItrm.showTextBadge(unread + "");
            }
        }



        TextView mText;
        ViewHolder(View itemView) {
            super(itemView);
            //mText = itemView.findViewById(R.id.item_tx);
        }
    }
}
