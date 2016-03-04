package com.example.contacts.contactss;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.contacts.contactss.api.Tut;
import com.example.contacts.contactss.fragments.Feed;
import com.example.contacts.contactss.fragments.Modules;
import com.example.contacts.contactss.model.NewsItem;
import com.example.contacts.contactss.utils.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    static final String MODULES_TAG = "MODULES_TAG";
    static final String FEED_TAG = "FEED_TAG";


    private Feed feedFragment;
    private Modules modulesFragment;
    private Fragment selectedFragment = null;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        setNavigationDrawer();
        setFab();
        showFeed();
    }

    private void setFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tut.asyncGetNews(Constants.TusRss, new Tut.CallBackGetNews() {
                    @Override
                    public void onSuccess(ArrayList<NewsItem> model) {
                        /*feedRecyclerView.setAdapter(new News(model, MainActivity.this));*/
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });
    }

    private void setNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void showFeed() {
        if(!(selectedFragment instanceof Feed)) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            hideFragments();
            if(null == feedFragment) {
                feedFragment = new Feed();
                fragmentTransaction.add(R.id.mainPager, feedFragment, FEED_TAG);
            }
            fragmentTransaction.show(feedFragment);
            fragmentTransaction.commit();
            getFragmentManager().executePendingTransactions();
            selectedFragment = feedFragment;
        }
    }

    private void showModules() {
        if(!(selectedFragment instanceof Modules)) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            hideFragments();
            if(null == modulesFragment) {
                modulesFragment = new Modules();
                fragmentTransaction.add(R.id.mainPager, modulesFragment, MODULES_TAG);
            }
            fragmentTransaction.show(modulesFragment);
            fragmentTransaction.commit();
            getFragmentManager().executePendingTransactions();
            selectedFragment = modulesFragment;
        }
    }

    private void hideFragments() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        feedFragment = (Feed) getFragmentManager().findFragmentByTag(FEED_TAG);
        if(null != feedFragment) {
            fragmentTransaction.hide(feedFragment);
        }

        modulesFragment = (Modules) getFragmentManager().findFragmentByTag(MODULES_TAG);
        if(null != modulesFragment) {
            fragmentTransaction.hide(modulesFragment);
        }

        fragmentTransaction.commit();
        getFragmentManager().executePendingTransactions();
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
