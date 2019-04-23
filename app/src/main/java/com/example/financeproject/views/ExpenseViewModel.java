package com.example.financeproject.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.financeproject.models.Item;

import java.util.List;

public class ExpenseViewModel extends ViewModel {
    private MutableLiveData<List<Item>> items;
    public LiveData<List<Item>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();
            loadItems();
        }
        return items;
    }

    private void loadItems() {}
}
