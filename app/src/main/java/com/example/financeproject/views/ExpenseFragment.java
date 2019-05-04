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

import java.util.List;

public class ExpenseFragment extends Fragment {

    private ExpenseViewModel mViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static ExpenseFragment newInstance() {
        return new ExpenseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.expense_fragment, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        loadItems();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
    }

    public void loadItems() {
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        List<Expense> expenses = db.expenseDao().getAll();
        mAdapter = new ExpenseAdapter(expenses);
        recyclerView.setAdapter(mAdapter);
    }

}
