package com.example.contacts.contactss;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.contacts.contactss.adapter.News;
import com.example.contacts.contactss.api.Tut;
import com.example.contacts.contactss.model.NewsItem;
import com.example.contacts.contactss.utils.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView feedRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setRecycler();
        setFab();
    }

    public void setRecycler() {
        feedRecyclerView = (RecyclerView) findViewById(R.id.newsFeedRecyclerView);
        feedRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        feedRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tut.asyncGetNews(Constants.TusRss, new Tut.CallBackGetNews() {
                    @Override
                    public void onSuccess(ArrayList<NewsItem> model) {
                        feedRecyclerView.setAdapter(new News(model, MainActivity.this));
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
