package com.example.financeproject.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface IncomeDao {
    @Query("SELECT * FROM Income")
    List<Income> getAll();

    @Insert
    void insertAll(Income... incomes);

    @Insert
    void insert(Income income);

    @Delete
    void delete(Income income);
}
