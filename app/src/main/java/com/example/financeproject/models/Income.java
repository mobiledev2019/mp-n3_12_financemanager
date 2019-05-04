package com.example.financeproject.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Income {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private Double amount;
    private Date date;

    public Income() {}

}
