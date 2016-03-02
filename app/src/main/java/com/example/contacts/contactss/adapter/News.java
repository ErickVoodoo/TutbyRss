package com.example.contacts.contactss.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contacts.contactss.R;
import com.example.contacts.contactss.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 01.03.2016.
 */
public class News extends RecyclerView.Adapter<News.ViewHolder> {

    ArrayList<NewsItem> arrayList;
    Activity activity;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
        holder.description.setText(Html.fromHtml(arrayList.get(position).getDescription()).toString().substring(1,Html.fromHtml(arrayList.get(position).getDescription()).length() ));
        holder.pubDate.setText(arrayList.get(position).getPubDate());
        holder.link.setText(arrayList.get(position).getLink());
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
        public TextView pubDate;
        public TextView link;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.newImage);
            title = (TextView) itemView.findViewById(R.id.newTitle);
            description = (TextView) itemView.findViewById(R.id.newDescription);
            category = (TextView) itemView.findViewById(R.id.newMainCategory);
            pubDate = (TextView) itemView.findViewById(R.id.newPubDate);
            link = (TextView) itemView.findViewById(R.id.newLink);
        }
    }

    public News(ArrayList<NewsItem> arrayList, Activity activity) {
        this.arrayList = arrayList;
        this.activity = activity;
    }
}
