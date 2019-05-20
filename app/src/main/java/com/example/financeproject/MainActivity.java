package com.example.financeproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.financeproject.ui.main.SectionsPagerAdapter;
import com.example.financeproject.views.ChartFragment;
import com.example.financeproject.views.ExpenseFragment;
import com.example.financeproject.views.IncomeFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    final Calendar myCalendar = Calendar.getInstance();
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), myCalendar);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton addExpenseButton = findViewById(R.id.add_expense_btn);
        FloatingActionButton addIncomeButton = findViewById(R.id.add_income_btn);
        dateTextView = findViewById(R.id.date_textview);

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivityForResult(intent, 1);
            }
        });

        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddIncomeActivity.class);
            startActivityForResult(intent, 1);
            }
        });

        updateDateText();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateText();
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                refreshFragments();
            }
        }
    }

    private void refreshFragments() {
        Fragment expenseFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":0");
        Fragment incomeFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":1");
        Fragment chartFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":2");
        if (expenseFragment != null) {
            ((ExpenseFragment) expenseFragment).loadItems(myCalendar.getTime());
        }
        if (incomeFragment != null) {
            ((IncomeFragment) incomeFragment).loadItems(myCalendar.getTime());
        }
        if (chartFragment != null) {
            ((ChartFragment) chartFragment).refreshChart(myCalendar.getTime());
        }
    }

    private void updateDateText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        dateTextView.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void nextMonth(View view) {
        myCalendar.add(Calendar.MONTH, 1);
        updateDateText();
        refreshFragments();
    }

    public void prevMonth(View view) {
        myCalendar.add(Calendar.MONTH, -1);
        updateDateText();
        refreshFragments();
    }
}
