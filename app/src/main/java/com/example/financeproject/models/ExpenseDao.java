package com.example.financeproject.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    List<Expense> getAll();

    @Insert
    void insertAll(Expense... expenses);

    @Insert
    void insert(Expense expense);

    @Delete
    void delete(Expense expense);
}
