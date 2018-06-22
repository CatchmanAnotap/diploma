package com.example.catchman.studlife.components;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


import com.example.catchman.studlife.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alan on 16.01.2018.
 */

public class ViewPagerCircleIndicator extends HorizontalScrollView {
    private LinearLayout main;

    private ViewPager viewPager;
    private View nextButton;
    private List<Fragment> fragments;
    private PagerAdapter adapter;
    private FragmentManager fragmentManager;
    private OnLastItemSkippedListener onLastItemSkippedListener;

    public ViewPagerCircleIndicator(Context context) {
        super(context);
        prepare();
    }

    public ViewPagerCircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        prepare();
    }

    public ViewPagerCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepare();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewPagerCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        prepare();
    }

    private void prepare() {
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        main = new LinearLayout(getContext());
        main.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        main.removeAllViews();
        main.setOrientation(LinearLayout.HORIZONTAL);
        main.setGravity(Gravity.CENTER);
        addView(main);
    }

    public ViewPagerCircleIndicator withViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        return this;
    }

    public ViewPagerCircleIndicator withNextButton(View nextButton) {
        this.nextButton = nextButton;
        return this;
    }

    public ViewPagerCircleIndicator withFragments(Collection<Fragment> fragments) {
        this.fragments = new ArrayList<>(fragments);
        return this;
    }

    public ViewPagerCircleIndicator withAdapter(PagerAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public void init() {
        if (fragmentManager != null || adapter != null) {
            if (fragments != null || adapter != null) {
                if (viewPager != null) {
                    if (adapter == null) {
                        adapter = new MyFragmentPagerAdapter(fragmentManager);
                    }
                    viewPager.setAdapter(adapter);
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            if (position == 0) {
                                smoothScrollTo(0, 0);
                            } else {
                                smoothScrollTo(main.getChildAt(position - 1).getLeft(), 0);
                            }
                            for (int i = 0; i < adapter.getCount(); i++) {
                                main.getChildAt(i).setLayoutParams(i == position ? getSelectedLayoutParams() : getUnselectedLayoutParams());
                                ViewCompat.setBackground(main.getChildAt(i), i == position ? getSelectedBg() : getUnselectedBg());
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });


                    main.removeAllViews();
                    for (int i = 0; i < adapter.getCount(); i++) {
                        main.addView(getCircleView(i));
                    }

                    if (nextButton != null) {
                        nextButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = viewPager.getCurrentItem();
                                if (pos == adapter.getCount() - 1) {
                                    if (onLastItemSkippedListener != null) {
                                        onLastItemSkippedListener.onLastItemSkipped();
                                    }
                                } else {
                                    viewPager.setCurrentItem(pos + 1);
                                }
                            }
                        });
                    }
                } else {
                    throw new IllegalArgumentException("ViewPager is not set");
                }
            } else {
                throw new IllegalArgumentException("Either fragments or adapter are not set");
            }
        } else {
            throw new IllegalArgumentException("Either fragments or fragmentManager are not set");
        }
    }

    public ViewPagerCircleIndicator withFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public ViewPagerCircleIndicator withOnLastItemSkippedListener(OnLastItemSkippedListener onLastItemSkippedListener) {
        this.onLastItemSkippedListener = onLastItemSkippedListener;
        return this;
    }

    private int getPxValue(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getContext().getResources().getDisplayMetrics());
    }

    private View getCircleView(int pos) {
        LinearLayout view = new LinearLayout(getContext());
        ViewCompat.setBackground(view, pos == 0 ? getSelectedBg() : getUnselectedBg());
        view.setLayoutParams(pos == 0 ? getSelectedLayoutParams() : getUnselectedLayoutParams());
        return view;
    }

    private Drawable getSelectedBg() {
        return ContextCompat.getDrawable(getContext(), R.drawable.bg_selected);
    }

    private Drawable getUnselectedBg() {
        return ContextCompat.getDrawable(getContext(), R.drawable.bg_unselected);
    }

    private LinearLayout.LayoutParams getSelectedLayoutParams() {
        int margin = getPxValue(5);
        int size = getPxValue(10);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        layoutParams.setMargins(margin, margin, margin, margin);
        return layoutParams;
    }

    private LinearLayout.LayoutParams getUnselectedLayoutParams() {
        int margin = getPxValue(5);
        int size = getPxValue(10);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        layoutParams.setMargins(margin, margin, margin, margin);
        return layoutParams;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public View getNextButton() {
        return nextButton;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }


    public interface OnLastItemSkippedListener {
        void onLastItemSkipped();
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}
