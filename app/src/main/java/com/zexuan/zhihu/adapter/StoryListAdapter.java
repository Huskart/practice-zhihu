package com.zexuan.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zexuan.zhihu.AnswerActivity;
import com.zexuan.zhihu.R;

import java.util.List;

import zhihu.zexuan.com.model.story.Data;

/**
 * Created by Zexuan on 2016/4/7.
 */
public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.StoryViewHolder> {

    private List<Data> datas;
    private Context context;
    private ImageLoader imageLoader;
    DisplayImageOptions options;

    public StoryListAdapter(Context context, List<Data> datas) {
        this.context = context;
        this.datas = datas;
        this.imageLoader = ImageLoader.getInstance();
        this.options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.actor_avator)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.story_item, parent, false);
        return new StoryViewHolder(v);
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Data data = datas.get(position);
        String verb = data.getVerb();

        if(("PROMOTION_ARTICLE").equals(verb)){
            imageLoader.displayImage(data.getTarget().getAuthor().getAvatar_url(), holder.avatarIv, options);
            holder.avatarIv.setTag(data.getTarget().getAuthor().getAvatar_url());
            holder.titleTv.setText(datas.get(position).getTarget().getTitle());
            holder.attentionTv.setText("去往专栏");
        }else if("TOPIC_ACKNOWLEDGED_ANSWER".equals(verb)){
            imageLoader.displayImage(data.getActors().get(0).getAvatar_url() , holder.avatarIv , options);
            holder.avatarIv.setTag(data.getActors().get(0).getAvatar_url());
            holder.titleTv.setText(data.getTarget().getQuestion().getTitle());
            holder.attentionTv.setText("关注问题");
        }else if("PROMOTION_ANSWER".equals(verb)){
            imageLoader.displayImage(data.getTarget().getAuthor().getAvatar_url() , holder.avatarIv , options);
            holder.avatarIv.setTag(data.getTarget().getQuestion().getAuthor().getAvatar_url());
            holder.titleTv.setText(data.getTarget().getQuestion().getTitle());
            holder.attentionTv.setText("关注问题");
        }

        if(data.getTarget().getImage_url() != null){
            holder.hdImage.setVisibility(View.VISIBLE);
            imageLoader.displayImage(data.getTarget().getImage_url(), holder.hdImage , options);
            holder.hdImage.setTag(data.getTarget().getImage_url());
        }else {
            holder.hdImage.setVisibility(View.GONE);
        }

        holder.actorTv.setText(data.getAction_text());
        holder.contentTv.setText(data.getTarget().getExcerpt());
        int commentCount = datas.get(position).getTarget().getComment_count();
        int voteUpCount = datas.get(position).getTarget().getVoteup_count();
        holder.voteNumTv.setText(voteUpCount + " 赞同 · ");
        holder.commentCountTv.setText(commentCount + " 评论 · ");

        holder.itemContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start new Activity
                context.startActivity(new Intent(context, AnswerActivity.class));
            }
        });


//        if(data.getTarget() == null){
//            holder.avatarIv.setImageDrawable(context.getDrawable(R.drawable.author_avator));
//            holder.contentTv.setVisibility(View.GONE);
//        }else {
//            holder.contentTv.setVisibility(View.VISIBLE);
//            holder.contentTv.setText(datas.get(position).getTarget().getExcerpt());
//            imageLoader.displayImage(data.getTarget().getAuthor().getAvatar_url(), holder.avatarIv, options);
//
//        }
//
//        if(data.getTarget().getImage_url() != null && !data.getTarget().getImage_url().equals("")){
//            holder.hdImage.setVisibility(View.VISIBLE);
//            imageLoader.displayImage(data.getTarget().getImage_url(),holder.hdImage , options);
//        }else {
//            holder.hdImage.setVisibility(View.GONE);
//        }
//
//        holder.voteNumTv.setText(datas.get(position).getTarget().getVoteup_count() + "");
//        holder.actorTv.setText(datas.get(position).getAction_text());
//        if (datas.get(position).getTarget().getQuestion() != null) {
//            holder.titleTv.setText(datas.get(position).getTarget().getTitle());
//        } else {
//
//        }
//
//        int commentCount = datas.get(position).getTarget().getComment_count();
//        int voteUpCount = datas.get(position).getTarget().getVoteup_count();
//        holder.voteNumTv.setText(voteUpCount + " 赞同 · ");
//        holder.commentCountTv.setText(commentCount + " 评论 · ");
//        holder.attentionTv.setText("关注问题");

//        holder.itemContentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = datas.get(position).getTarget().getQuestion().getUrl();
//                Intent intent = new Intent(context, QuestionActivity.class);
//                intent.putExtra("url", url);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class StoryViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView avatarIv;
        TextView actorTv;
        TextView voteNumTv;
        TextView titleTv;
        TextView contentTv;
        TextView commentCountTv;
        TextView attentionTv;
        ImageView hdImage;
        RelativeLayout itemContentLayout;

        public StoryViewHolder(View itemView) {
            super(itemView);
            hdImage = (ImageView) itemView.findViewById(R.id.hd_image);
            attentionTv = (TextView) itemView.findViewById(R.id.attention_tv);
            commentCountTv = (TextView) itemView.findViewById(R.id.comment_count_tv);
            cardView = (CardView) itemView.findViewById(R.id.story_card_view);
            avatarIv = (ImageView) itemView.findViewById(R.id.from_avatar);
            actorTv = (TextView) itemView.findViewById(R.id.actor_tv);
            voteNumTv = (TextView) itemView.findViewById(R.id.voteup_count_tv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.story_content_tv);
            itemContentLayout = (RelativeLayout) itemView.findViewById(R.id.story_item_content_ll);
        }
    }
}
