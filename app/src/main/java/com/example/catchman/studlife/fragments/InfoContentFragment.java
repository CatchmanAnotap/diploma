package com.example.catchman.studlife.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.catchman.studlife.R;
import com.example.catchman.studlife.models.ContactResp;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoContentFragment extends Fragment {

    private ContactResp contactResp;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.tvName)
    TextView name;
    @BindView(R.id.tvPhone)
    TextView phone;
    @BindView(R.id.tvGroup)
    TextView group;
    @BindView(R.id.tvInfo)
    TextView info;


    public static Fragment getInstance(ContactResp contactResp) {
        InfoContentFragment fragment = new InfoContentFragment();
        fragment.contactResp = contactResp;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_content, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){
        Glide
                .with(this)
                .load(Uri.parse(contactResp.getPhoto()))
                .into(photo);
        name.setText(contactResp.getName());
        phone.setText(contactResp.getPhone());
        info.setText(contactResp.getSummary());
    }


}
