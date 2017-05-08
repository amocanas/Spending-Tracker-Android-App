package com.student.alina.spendingtracker.ui;

import android.content.Context;

import com.student.alina.spendingtracker.interfaces.IMainActivityListener;

/**
 * Created by Alina on 04/15/2017.
 */
public class MainFragment extends BaseFragment {

    protected IMainActivityListener mMainActivityListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivityListener = (IMainActivityListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMainActivityListener = null;
    }

}
