package com.example.catchman.studlife.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.catchman.studlife.adapters.NewsAdapter;
import com.example.catchman.studlife.R;
import com.example.catchman.studlife.models.NewsResp;
import com.example.catchman.studlife.network.Api;
import com.example.catchman.studlife.utils.RxUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 */
public class NewsFragment extends BaseFragment {

    @BindView(R.id.rvNewsItem)
    RecyclerView rvNews;
    private NewsAdapter mAdapter;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), rvNews.getChildAdapterPosition(v), Toast.LENGTH_LONG).show();

            //Toast.makeText(getActivity(), .toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflateWithLoadingIndicator(R.layout.fragment_news, container);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvNews.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvNews.setLayoutManager(mLayoutManager);

        setLoading(true);
        RxUtil.networkConsumer(Api.getInstance().getWebService().getNews(), new Consumer<List<NewsResp>>() {
            @Override
            public void accept(List<NewsResp> newsResps) throws Exception {
                mAdapter = new NewsAdapter(onClickListener, newsResps);
                rvNews.setAdapter(mAdapter);
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
