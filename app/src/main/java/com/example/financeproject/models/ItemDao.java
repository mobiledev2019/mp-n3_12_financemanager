package com.example.financeproject.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Insert
    void insertAll(Item... items);

    @Insert
    void insert(Item item);

    @Delete
    void delete(Item item);
}
