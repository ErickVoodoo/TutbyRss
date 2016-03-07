package com.example.contacts.contactss.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.contactss.R;
import com.example.contacts.contactss.model.FeedItem;

import java.util.ArrayList;

/**
 * Created by erick on 2.3.16.
 */
public class Feed extends Fragment {
    View view;

    RecyclerView feedRecyclerView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);

        setRecycler();
        return  view;
    }

    public void setRecycler() {
        feedRecyclerView = (RecyclerView) view.findViewById(R.id.newsFeedRecyclerView);
        feedRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        feedRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setRecyclerArray(ArrayList<FeedItem> model) {
        feedRecyclerView.setAdapter(new com.example.contacts.contactss.adapter.Feed(model, this.getActivity()));
    }
}
