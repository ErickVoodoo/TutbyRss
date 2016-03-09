package com.example.contacts.contactss.adapter;

import android.animation.Animator;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.contacts.contactss.MainActivity;
import com.example.contacts.contactss.R;

/**
 * Created by erick on 5.3.16.
 */
public class Drawer extends RecyclerView.Adapter<Drawer.ViewHolder> {
    String[] stringArray = new String[0];
    Activity activity;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(stringArray[position]);
        holder.textView.setAlpha(0);
        holder.textView.animate().alpha(1).setDuration(200).setStartDelay(100 * position).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                holder.textView.setAlpha(1);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    ((MainActivity) activity).showModules();
                } else
                if (position == 1) {
                    ((MainActivity) activity).showFeed();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.drawerString);
        }
    }

    public Drawer(Activity activity) {
        if(null != activity) {
            this.activity = activity;
            stringArray = this.activity.getResources().getStringArray(R.array.drawer);
        }
    }
}
