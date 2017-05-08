package com.student.alina.spendingtracker;

import android.app.Application;
import android.content.Context;

/**
 * Created by Alina on 04/15/2017.
 */
public class ExpenseTrackerApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

}
