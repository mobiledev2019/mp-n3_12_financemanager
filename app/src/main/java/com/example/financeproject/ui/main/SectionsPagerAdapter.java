package com.example.financeproject.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.financeproject.R;
import com.example.financeproject.views.ChartFragment;
import com.example.financeproject.views.ExpenseFragment;
import com.example.financeproject.views.IncomeFragment;

import java.util.Calendar;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    private final Calendar calendar;

    public SectionsPagerAdapter(Context context, FragmentManager fm, Calendar calendar) {
        super(fm);
        mContext = context;
        this.calendar = calendar;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ExpenseFragment.newInstance(calendar.getTime());
            case 1:
                return IncomeFragment.newInstance(calendar.getTime());
            default:
                return ChartFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}