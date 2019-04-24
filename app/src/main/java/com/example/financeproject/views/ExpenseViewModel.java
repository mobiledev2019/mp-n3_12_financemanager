package com.example.financeproject.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.financeproject.models.Expense;

import java.util.List;

public class ExpenseViewModel extends ViewModel {
    private MutableLiveData<List<Expense>> items;
    public LiveData<List<Expense>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();
            loadItems();
        }
        return items;
    }

    private void loadItems() {}
}
