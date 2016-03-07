package com.example.contacts.contactss.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.contacts.contactss.R;
import com.example.contacts.contactss.model.FeedItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 01.03.2016.
 */
public class Feed extends RecyclerView.Adapter<Feed.ViewHolder> {

    ArrayList<FeedItem> arrayList;
    Activity activity;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(activity)
                .load(arrayList.get(position).getImage())
                .into(holder.image);
        if(arrayList.get(position).getImage().length() == 0) {
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setVisibility(View.VISIBLE);
        }
        holder.title.setText(arrayList.get(position).getTitle());
        holder.category.setText(arrayList.get(position).getCategory());
        holder.description.setText(Html.fromHtml(arrayList.get(position).getDescription()).toString().substring(1, Html.fromHtml(arrayList.get(position).getDescription()).length()));
//        holder.pubDate.setText(arrayList.get(position).getPubDate());
//        holder.link.setText(arrayList.get(position).getLink());

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.main.getLayoutParams();
        Display display = activity.getWindowManager().getDefaultDisplay();
        params.width = display.getWidth() - display.getWidth()/5;
        holder.main.setLayoutParams(params);
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrayList.get(position).getLink()));
                activity.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title;
        public TextView description;
        public TextView category;
//        public TextView pubDate;
//        public TextView link;
        public RelativeLayout main;

        public ViewHolder(View itemView) {
            super(itemView);

            main = (RelativeLayout) itemView.findViewById(R.id.mainRelative);
            image = (ImageView) itemView.findViewById(R.id.newImage);
            title = (TextView) itemView.findViewById(R.id.newTitle);
            description = (TextView) itemView.findViewById(R.id.newDescription);
            category = (TextView) itemView.findViewById(R.id.newMainCategory);
//            pubDate = (TextView) itemView.findViewById(R.id.newPubDate);
//            link = (TextView) itemView.findViewById(R.id.newLink);
        }
    }

    public Feed(ArrayList<FeedItem> arrayList, Activity activity) {
        this.arrayList = arrayList;
        this.activity = activity;
    }
}
