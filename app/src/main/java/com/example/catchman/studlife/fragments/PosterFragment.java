package com.example.catchman.studlife.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catchman.studlife.R;
import com.example.catchman.studlife.adapters.NewsAdapter;
import com.example.catchman.studlife.adapters.PosterAdapter;
import com.example.catchman.studlife.models.PosterResp;
import com.example.catchman.studlife.network.Api;
import com.example.catchman.studlife.utils.RxUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;


/**
 * A simple {@link Fragment} subclass.
 */
public class PosterFragment extends BaseFragment {

    @BindView(R.id.rvPoster)
    RecyclerView rvPoster;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflateWithLoadingIndicator(R.layout.fragment_poster, container);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){
        setLoading(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvPoster.setLayoutManager(mLayoutManager);
        RxUtil.networkConsumer(Api.getInstance().getWebService().getPoster(), new Consumer<List<PosterResp>>() {
            @Override
            public void accept(List<PosterResp> posterResps) throws Exception {
                for (int i = 0; i < posterResps.size(); i++) {
                    Log.e("$#@", "accept: " + posterResps.get(i));
                }
                PosterAdapter mAdapter = new PosterAdapter(posterResps, getContext());
                rvPoster.setAdapter(mAdapter);
                setLoading(false);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                setLoading(false);
            }
        });
    }

}
