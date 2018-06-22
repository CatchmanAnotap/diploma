package com.example.catchman.studlife;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.catchman.studlife.fragments.BaseFragment;


public class FragmentUtil {
    public static void replaceFragment(final FragmentManager manager, Fragment fragment,
                                       final boolean addToBackStack, boolean useLeftRightAnimations) {
        final FragmentTransaction fTrans;
        fTrans = manager.beginTransaction();
        fTrans.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        if (useLeftRightAnimations) {
            fTrans.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left);
        } else {
            fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (addToBackStack) {
                    fTrans.addToBackStack(null);
                } else {
                    manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                fTrans.commitAllowingStateLoss();
            }
        });
    }

    public static void replaceFragment(FragmentManager manager, BaseFragment fragment, boolean addToBackStack) {
        replaceFragment(manager, fragment, addToBackStack, false);
    }
}