package com.example.catchman.studlife.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catchman.studlife.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewInfoFragment extends BaseFragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_info, container, false);
    }

}
