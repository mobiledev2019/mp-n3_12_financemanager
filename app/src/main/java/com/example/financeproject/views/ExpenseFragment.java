package com.example.financeproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.financeproject.AppDatabase;
import com.example.financeproject.R;
import com.example.financeproject.models.Expense;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpenseFragment extends Fragment {

    private ExpenseViewModel mViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Date date;

    public static ExpenseFragment newInstance(Date date) {
        ExpenseFragment fragment = new ExpenseFragment();
        fragment.setDate(date);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.expense_fragment, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        loadItems(date);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
    }

    public void loadItems(Date date) {
        AppDatabase db = AppDatabase.getAppDatabase(getContext());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        long firstDay = calendar.getTime().getTime();

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        long lastDay = calendar.getTime().getTime();

        List<Expense> expenses = db.expenseDao().getAllFromMonth(
                firstDay,
                lastDay);
        mAdapter = new ExpenseAdapter(expenses);
        recyclerView.setAdapter(mAdapter);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

}
