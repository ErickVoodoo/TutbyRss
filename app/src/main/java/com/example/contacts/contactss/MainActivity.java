package com.example.contacts.contactss;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.contacts.contactss.adapter.Drawer;
import com.example.contacts.contactss.api.Tut;
import com.example.contacts.contactss.fragments.Feed;
import com.example.contacts.contactss.fragments.Modules;
import com.example.contacts.contactss.model.FeedItem;
import com.example.contacts.contactss.utils.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    static final String MODULES_TAG = "MODULES_TAG";
    static final String FEED_TAG = "FEED_TAG";


    private Feed feedFragment;
    private Modules modulesFragment;
    private Fragment selectedFragment = null;

    String url;

    DrawerLayout drawerLayout;
    RecyclerView drawerRecycler;
    Constants constants;
    String SELECTED_FEED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constants = new Constants();
        SELECTED_FEED = constants.ModulesArray.get(0).getUrl();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        setDrawerLayout();
        setFab();
        setRecycler();
        showFeed();
        getSupportActionBar().setTitle(constants.ModulesArray.get(0).getName());
    }

    private void setFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFragment instanceof Feed &&
                        null != feedFragment) {
                    Tut.asyncGetNews(SELECTED_FEED, new Tut.CallBackGetNews() {
                        @Override
                        public void onSuccess(ArrayList<FeedItem> model) {
                            feedFragment.setRecyclerArray(model);
                        }

                        @Override
                        public void onError(String error) {

                        }
                    });
                }
            }
        });
    }

    private void setDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.e("STATE", String.valueOf(newState));
                if (newState == DrawerLayout.STATE_IDLE) {
                    drawerRecycler.setAdapter(new Drawer(MainActivity.this));
                } else {
                    drawerRecycler.setAdapter(new Drawer(null));
                }
            }
        });
    }

    public void setRecycler() {
        drawerRecycler = (RecyclerView) findViewById(R.id.drawerRecycler);
        drawerRecycler.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        drawerRecycler.setLayoutManager(linearLayoutManager);
    }

    public void selectNewFeed(Constants.ModulesClass module) {
        SELECTED_FEED = module.getUrl();
        getSupportActionBar().setTitle(module.getName());
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void showFeed() {
        if(!(selectedFragment instanceof Feed)) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            hideFragments();
            getSupportActionBar().setTitle("Feed");
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

    public void showModules() {
        if(!(selectedFragment instanceof Modules)) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            hideFragments();
            getSupportActionBar().setTitle("Modules");
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
        drawerLayout.closeDrawer(GravityCompat.START);
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
