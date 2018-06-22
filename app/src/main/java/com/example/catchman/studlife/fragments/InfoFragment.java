package com.example.catchman.studlife.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catchman.studlife.R;
import com.example.catchman.studlife.adapters.TabsFragmentAdapter;
import com.example.catchman.studlife.models.ContactResp;
import com.example.catchman.studlife.network.Api;
import com.example.catchman.studlife.utils.RxUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;


/**
 * A simple {@link Fragment} subclass.

 */
public class InfoFragment extends BaseFragment {

    private List<ContactResp> contacts;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflateWithLoadingIndicator(R.layout.fragment_info, container);
        ButterKnife.bind(this, view);
        init();
//        mList.add(getString(R.string.info_of_town));
//        mList.add(getString(R.string.list_of_town));
//
//        TabsFragmentAdapter adapter = new TabsFragmentAdapter(getSupportFragmentManager(), mList);
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


    private void init() {
        setLoading(true);
        RxUtil.networkConsumer(Api.getInstance().getWebService().getContacts(), new Consumer<List<ContactResp>>() {
            @Override
            public void accept(final List<ContactResp> contactsList) throws Exception {
                contacts = new ArrayList<>(contactsList);
                initTabs();
                setLoading(false);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //checkAuth(throwable);
                setLoading(false);
            }
        });


    }

    private void initTabs() {

        TabsFragmentAdapter adapter = new TabsFragmentAdapter(getMainFragmentManager(), contacts);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
