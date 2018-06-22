package com.example.catchman.studlife.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.catchman.studlife.fragments.InfoContentFragment;
import com.example.catchman.studlife.models.ContactResp;

import java.util.List;

/**
 * Created by catchman on 6/14/18.
 */

public class TabsFragmentAdapter extends FragmentPagerAdapter {
    private List<ContactResp> mList;

    public TabsFragmentAdapter(FragmentManager fragmentManager, List<ContactResp> list) {
        super(fragmentManager);
        mList = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getPosition();
    }

    @Override
    public Fragment getItem(int position) {
        return InfoContentFragment.getInstance(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
