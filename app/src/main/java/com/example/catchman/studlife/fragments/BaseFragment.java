package com.example.catchman.studlife.fragments;


import android.animation.LayoutTransition;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.catchman.studlife.R;

import butterknife.OnClick;
import butterknife.Optional;

public abstract class BaseFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;



    protected void setLoading(boolean loading) {
        swipeRefreshLayout.setRefreshing(loading);
    }

    protected boolean isLoading() {
        return swipeRefreshLayout.isRefreshing();
    }

    protected FragmentManager getMainFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

//    @Optional
//    @OnClick(R.id.buttonBack)
//    public void onBackClick() {
//        getActivity().onBackPressed();
//    }

    protected void showKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    protected View inflateWithLoadingIndicator(int resId, ViewGroup parent) {
        swipeRefreshLayout = new SwipeRefreshLayout(getActivity());
        swipeRefreshLayout.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        swipeRefreshLayout.setEnabled(false);
        View view = LayoutInflater.from(getActivity()).inflate(resId, parent, false);
        swipeRefreshLayout.addView(view);
        return swipeRefreshLayout;
    }

    protected void hideKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.clearFocus();
    }

    protected void goBack() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    protected void animateLayout(ViewGroup viewGroup) {
        LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
        //layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    }

    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    protected int getColor(int resId) {
        return ContextCompat.getColor(getActivity(), resId);
    }

    public void startActivitySliding(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void startActivitySlidingFromRight(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
