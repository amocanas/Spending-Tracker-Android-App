package com.student.alina.spendingtracker.interfaces;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Alina on 04/15/2017.
 */


@IntDef({IExpensesType.MODE_EXPENSES, IExpensesType.MODE_INCOME})
@Retention(RetentionPolicy.SOURCE)
public @interface IExpensesType{
    int MODE_EXPENSES = 0;
    int MODE_INCOME = 1;
}

