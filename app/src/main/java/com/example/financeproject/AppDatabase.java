package com.example.financeproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.financeproject.models.Converters;
import com.example.financeproject.models.Expense;
import com.example.financeproject.models.ExpenseDao;
import com.example.financeproject.models.Income;
import com.example.financeproject.models.IncomeDao;

@Database(entities = {Expense.class, Income.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract ExpenseDao expenseDao();
    public abstract IncomeDao incomeDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
