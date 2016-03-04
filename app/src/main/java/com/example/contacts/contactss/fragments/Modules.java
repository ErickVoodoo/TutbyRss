package com.example.contacts.contactss.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.contactss.R;

/**
 * Created by erick on 2.3.16.
 */
public class Modules extends Fragment {
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_modules, container, false);
        return  view;
    }
}
